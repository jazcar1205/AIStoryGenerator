package model;

import service.OpenAIService;

public class FantasyStrategy implements StoryStrategy {
    private final OpenAIService service;

    public FantasyStrategy(OpenAIService service) {
        this.service = service;
    }

    @Override
    public String generateStory(String prompt) {
        try {
            return service.generateText("Fantasy story: " + prompt, 0.7, 500);
        } catch (Exception e) {
            return "Error generating story: " + e.getMessage();
        }
    }
}
