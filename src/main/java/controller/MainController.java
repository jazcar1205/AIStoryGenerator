package controller;

import model.*;
import persistence.Session;
import service.APIErrorHandler;
import service.OpenAIService;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {

    private final StoryModel model;
    private StoryStrategy strategy;
    private OpenAIService service;
    private final ExecutorService executor;

    public MainController(StoryModel model, OpenAIService service) {
        this.model = model;
        this.service = service;
        this.executor = Executors.newSingleThreadExecutor(); // Single-threaded executor
    }

    public void setStrategy(StoryStrategy strategy)
    {
        this.model.setStrategy(strategy.getStrategyType());
        this.strategy = strategy;
    }

    public void setStrategy(StrategyType strategy)
    {
        this.model.setStrategy(strategy);
        this.strategy = StoryStrategyFactory.getStrategy(strategy, service);
    }


    /**
     * Generates a story asynchronously using the selected strategy or fallback client.
     *
     * @param prompt The story prompt
     */
    public void generateStory(String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            // NOTE: Even this uses JOptionPane. You should change this too if you want a pure CLI.
            JOptionPane.showMessageDialog(null, "Please enter a prompt to generate a story.");
            return;
        }

        executor.submit(() -> {
            try {
                String story;

                if (strategy != null) {
                    // Pass Length and Complexity to strategy if needed
                    story = strategy.generateStory(prompt, model.getLength(), model.getComplexity());
                } else {
                    // Fallback to APIClient (async)
                    APIClient client = APIClient.getInstance();
                    client.generateStoryAsync(
                            prompt,
                            generatedStory -> SwingUtilities.invokeLater(() -> model.setStory(generatedStory)),
                            // FIX 1: Replace GUI error handler with System.err.println
                            error -> System.err.println("API Client Fallback Error: " + APIErrorHandler.handleError(error))
                    );
                    return;
                }

                // Update model on EDT
                SwingUtilities.invokeLater(() -> model.setStory(story));

            } catch (Exception e) {
                // FIX 2: Replace GUI error handler with System.err.println
                System.err.println("Strategy Execution Error: " + APIErrorHandler.handleError(e));
            }
        });
    }

    /**
     * Shutdown executor to avoid memory leaks on app close
     */
    public void shutdown() {
        executor.shutdown();
    }
}
