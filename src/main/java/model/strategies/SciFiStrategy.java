package model.strategies;

import model.Complexity;
import model.Length;
import model.StrategyType;
import service.OpenAIService;

public class SciFiStrategy extends StoryStrategy
{

    public SciFiStrategy(OpenAIService service) { super(service);}

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity) throws Exception {
        String fullPrompt = length + " " + complexity + " sci-fi story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }

    @Override
    public StrategyType getStrategyType()
    {
        return StrategyType.SCIFI;
    }
}