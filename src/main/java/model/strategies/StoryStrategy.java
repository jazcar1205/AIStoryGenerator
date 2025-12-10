package model.strategies;

import model.*;
import service.OpenAIService;

public abstract class StoryStrategy {
    protected final OpenAIService service;

    public StoryStrategy(OpenAIService service) {
        this.service = service;
    }

    public abstract String generateStory(String prompt, Length length, Complexity complexity, String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters) throws Exception;

    public abstract StrategyType getStrategyType();
}
