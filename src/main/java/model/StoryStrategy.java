package model;

import service.OpenAIService;

public abstract class StoryStrategy {
    protected final OpenAIService service;

    public StoryStrategy(OpenAIService service) {
        this.service = service;
    }

    public abstract String generateStory(String prompt, Length length, Complexity complexity) throws Exception;
}
