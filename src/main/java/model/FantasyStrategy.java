package model;

import service.OpenAIService;

public class FantasyStrategy extends StoryStrategy {

    public FantasyStrategy(OpenAIService service) { super(service);}

    @Override
    public String generateStory(String prompt, Length length, Complexity complexity) throws Exception {
        String fullPrompt = length + " " + complexity + " fantasy story: " + prompt;
        return service.generateText(fullPrompt, 0.7, 500);
    }
}
