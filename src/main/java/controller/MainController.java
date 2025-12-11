package controller;

import model.*;
import model.options.StrategyType;
import model.options.World;
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
        this.service = service;
        setStrategy(model.getStrategy());
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
        //String story, String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters
        this.model.setComplexity(model.getComplexity());
        this.model.setLength(model.getLength());
        this.model.setStrategy(model.getStrategy());
        this.model.setStrategy(model.getStrategy());
        setStrategy(model.getStrategy());
        this.model.setWorld(new World(model.getSetting(), model.getTimePeriod()));
        this.model.setTone(model.getTone());
        this.model.setPace(model.getPace());
        this.model.setPerspective(model.getPers());
        this.model.setCharacters(model.getCharacters());
        this.model.setPromptKeyWords(model.getPromptKeyWords());
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
     */
    public String generateStory() {
        if (model == null) {
            // NOTE: Even this uses JOptionPane. You should change this too if you want a pure CLI.
            JOptionPane.showMessageDialog(null, "Please enter a prompt to generate a story.");
            return "";
        }

        //executors use "Futures" aka a promised response to an execution.
        Future<String> storyFuture = executor.submit(() -> {
            try {
                if (strategy != null) {
                    // Synchronous generation.
                    String story = strategy.generateStory(model.getPromptKeyWords(), model.getLength(), model.getComplexity(), model.getSetting(), model.getTone().getTone(), model.getTimePeriod(), model.getPace(),model.getPers(),model.getCharacters().getCharacters());
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