package controller;

import model.*;
import model.strategies.StoryStrategy;
import persistence.Session;
import service.APIErrorHandler;
import service.OpenAIService;

import javax.swing.*;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainController {

    private StoryModel model;
    private StoryStrategy strategy;
    private final OpenAIService service;
    private final ExecutorService executor;

    public MainController(StoryModel model, OpenAIService service) {
        this.model = model;
        setStrategy(model.getStrategy());
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

    public String generateStoryDummy()
    {
        return model.toString();
    }

    public void updateModel(StoryModel model)
    {
        this.model.setComplexity(model.getComplexity());
        this.model.setLength(model.getLength());
        this.model.setStrategy(model.getStrategy());
        setStrategy(model.getStrategy());
    }

    public String getStory()
    {
        return model.getStory();
    }

    public Session getSession()
    {
        return model.getAsSession();
    }

    public void attachObserver(Observer observer)
    {
        model.addObserver(observer);
    }

    /**
     * Generates a story using the selected strategy.
     *
     * @param prompt The story prompt
     */
    public String generateStory(String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            // NOTE: Even this uses JOptionPane. You should change this too if you want a pure CLI.
            JOptionPane.showMessageDialog(null, "Please enter a prompt to generate a story.");
            return "";
        }

        //executors use "Futures" aka a promised response to an execution.
        Future<String> storyFuture = executor.submit(() -> {
            try {
                if (strategy != null) {
                    // Synchronous generation.
                    String story = strategy.generateStory(prompt, model.getLength(), model.getComplexity(), model.getSetting(), model.getTone(), model.getTimePeriod(), model.getPace(),model.getPers(),model.getCharacters());
                    return story;
                }else
                {
                    System.out.println("No strategy selected?");
                    return null;
                }
            } catch (Exception e) {
                // FIX 2: Replace GUI error handler with System.err.println
                System.err.println("Strategy Execution Error: " + APIErrorHandler.handleError(e));
                return null;
            }
        });

        //After generation is complete, we need to get the "Future".
        try {
            String storyResult = storyFuture.get(); // Will block thread from moving on until this is completed.
            System.out.println("Story successfully generated and retrieved from Future.");
            model.setStory(storyResult);
            return storyResult;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error retrieving story from executor: " + e.getMessage());
            return null;
        }
    }

    /**
     * Shutdown executor to avoid memory leaks on app close
     */
    public void shutdown() {
        executor.shutdown();
    }
}