package model.strategies;

import model.Complexity;
import model.Length;
import model.StrategyType;
import service.OpenAIService;

public class HorrorStrategy extends StoryStrategy
{

    public HorrorStrategy(OpenAIService service) {
        super(service);
    }

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity) throws Exception {
        String fullPrompt = length + " " + complexity + " horror story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }

    @Override
    public StrategyType getStrategyType()
    {
        return StrategyType.HORROR;
    }
}
