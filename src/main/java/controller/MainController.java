package controller;

import model.APIClient;
import model.StoryModel;
import model.StoryStrategy;
import service.APIErrorHandler;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {

    private final StoryModel model;
    private StoryStrategy strategy;
    private final ExecutorService executor;

    public MainController(StoryModel model) {
        this.model = model;
        this.executor = Executors.newSingleThreadExecutor(); // Single-threaded executor
    }

    public void setStrategy(StoryStrategy strategy) {
        this.strategy = strategy;
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
