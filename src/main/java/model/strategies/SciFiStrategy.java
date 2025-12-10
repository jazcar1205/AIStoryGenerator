package model.strategies;

import model.*;
import service.OpenAIService;

public class SciFiStrategy extends StoryStrategy
{

    public SciFiStrategy(OpenAIService service) { super(service);}

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity, String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters) throws Exception {
        String fullPrompt = "Length: "+length + ", Complexity: " + complexity +", Setting: " + setting+", Tone: " + tone+", Time Period: " + timePeriod+ ", Pace: " + pace+", Perspective: " + pers+", Characters: " + characters+ ", sci-fi story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }

    @Override
    public StrategyType getStrategyType()
    {
        return StrategyType.SCIFI;
    }
}