package persistence;
import model.Complexity;
import model.Length;
import model.StoryStrategy;
import model.StrategyType;

public class Session{

    private String filePath;
    private String story;
    private Complexity complexity;
    private Length length;
    private StrategyType strategyType;

    public Session() {
        this.story = "Filler text.";
        this.complexity = Complexity.CHILDFRIENDLY;
        this.length = Length.SHORT;
        this.strategyType = StrategyType.FANTASY;
    }

    public Session(Complexity complexity, Length length, StrategyType storyStrategy)
    {
        this.story = "";
        this.complexity = complexity;
        this.length = length;
        this.strategyType = storyStrategy;
    }
    public Session(String story, Complexity complexity, Length length, StrategyType storyStrategy)
    {
        this.story = story;
        this.complexity = complexity;
        this.length = length;
        this.strategyType = storyStrategy;
    }

    // Getters and setters
    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }

    public Complexity getComplexity()
    {
        return complexity;
    }

    public void setComplexity(Complexity complexity)
    {
        this.complexity = complexity;
    }

    public Length getLength()
    {
        return length;
    }

    public void setLength(Length length)
    {
        this.length = length;
    }

    public StrategyType getStoryStrategy()
    {
        return strategyType;
    }

    public void setStoryStrategy(StrategyType strategyType)
    {
        this.strategyType = strategyType;
    }

    @Override
    public String toString()
    {
        return "Session{" +
                "story='" + story + '\'' +
                ", complexity=" + complexity +
                ", length=" + length +
                ", strategyType=" + strategyType +
                '}';
    }
}
