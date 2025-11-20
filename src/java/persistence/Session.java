package persistence;


public class Session{

    private String story;
    private String prompt;
    private String strategyName;

    public Session() { }

    public Session(String story, String prompt, String strategyName) {
        this.story = story;
        this.prompt = prompt;
        this.strategyName = strategyName;
    }

    // Getters and setters
    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getStrategyName() { return strategyName; }
    public void setStrategyName(String strategyName) { this.strategyName = strategyName; }
}
