package model.strategies;

import model.options.*;
import service.OpenAIService;

public class GenericStrategy extends StoryStrategy
{

    public GenericStrategy(OpenAIService service) {
        super(service);
    }

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity, String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters) throws Exception {
        String fullPrompt = "Length: "+length + ", Complexity: " + complexity +", Setting: " + setting+", Tone: " + tone+", Time Period: " + timePeriod+ ", Pace: " + pace+", Perspective: " + pers+", Characters: " + characters+ ", story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.GENERIC;
    }
}
