package controller;

import model.APIClient;
import model.StoryModel;
import model.StoryStrategy;
import service.APIErrorHandler;

import javax.swing.*;

public class MainController {

    private final StoryModel model;
    private StoryStrategy strategy;

    public MainController(StoryModel model) {
        this.model = model;
    }

    public void setStrategy(StoryStrategy strategy) {
        this.strategy = strategy;
    }

    public void generateStory(String prompt) {
        if (strategy != null) {
            // Use strategy in a background thread
            new Thread(() -> {
                try {
                    String story = strategy.generateStory(prompt);
                    SwingUtilities.invokeLater(() -> model.setStory(story));
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null, APIErrorHandler.handleError(e))
                    );
                }
            }).start();
        } else {
            // Default: use APIClient directly (if implemented)
            APIClient client = APIClient.getInstance();
            client.generateStoryAsync(
                    prompt,
                    story -> SwingUtilities.invokeLater(() -> model.setStory(story)),
                    error -> SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null, APIErrorHandler.handleError(error))
                    )
            );
        }
    }
}
