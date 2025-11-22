package model;

import service.OpenAIService;

public class GenericStrategy extends StoryStrategy {

    public GenericStrategy(OpenAIService service) {
        super(service);
    }

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity) throws Exception {
        String fullPrompt = length + " " + complexity + " story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.GENERIC;
    }
}
