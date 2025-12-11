package persistence;
import model.*;
import model.options.*;

/**
 * Represents the current session.
 */
public class Session{

    private String filePath;
    private String story;
    private Complexity complexity;
    private Length length;
    private StrategyType strategyType;
    private Pace pace;
    private String promptKeyWords;
    private Perspective pers;
    private String timePeriod;
    private String setting;
    private String tone;
    private String characters;

    /**
     * Default constructor with filler text.
     */
    public Session() {
        this.story = "Filler text.";
        this.complexity = Complexity.CHILDFRIENDLY;
        this.length = Length.SHORT;
        this.strategyType = StrategyType.FANTASY;
        this.pace = Pace.NORMAL;
        this.pers = Perspective.THIRD;
        setting = "Any";
        tone = "Any";
        timePeriod ="Any";
        this.characters = "Any";
    }

    /**
     * Creates a session with the following options.
     * @param complexity
     * @param length
     * @param storyStrategy
     */
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

    public Session(String story, String promptKeyWords,Complexity complex, Length len, StrategyType strategy, Pace pace,
                   Perspective pers, World world, Tone tone, Characters characters) {
        this.story = story;
        this.promptKeyWords = promptKeyWords;
        this.length = len;
        this.complexity = complex;
        this.strategyType = strategy;
        this.pace = pace;
        this.pers = pers;
        this.setting = world.getSetting();
        this.tone = tone.getTone();
        this.timePeriod = world.getTimePeriod();
        this.characters = characters.getCharacters();
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

    public Pace getPace() {return pace;}
    public void setPace(Pace pace) {this.pace = pace;}

    public Perspective getPers() {return pers;}
    public void setPers(Perspective pers) {this.pers = pers;}

    public String getTimePeriod() {return timePeriod;}
    public void setTimePeriod(String timePeriod) {this.timePeriod = timePeriod;}

    public String getSetting() {return setting;}
    public void setSetting(String setting) {this.setting = setting;}

    public String getTone() {return tone;}
    public void setTone(String tone) {this.tone = tone;}

    public String getCharacters() {return characters;}
    public void setCharacters(String characters) {this.characters = characters;}

    public StrategyType getStoryStrategy()
    {
        return strategyType;
    }
    public void setStoryStrategy(StrategyType strategyType)
    {
        this.strategyType = strategyType;
    }

    /**
     * Get the session as a model.
     * @return
     */
    public StoryModel getAsModel()
    {
        return new StoryModel(this.story, this.promptKeyWords, this.length, this.complexity, this.strategyType, this.setting, this.tone, this.timePeriod, this.pace, this.pers, this.characters);
    }

    @Override
    public String toString()
    {
        return "Session{" +
                "story='" + story + '\'' +
                ", complexity=" + complexity +
                ", length=" + length +
                ", strategyType=" + strategyType +
                ", pace=" + pace +
                ", time period=" + timePeriod +
                ", tone=" + tone +
                ", perspective=" + pers +
                ", setting=" + setting +
                ", characters=" + characters +
                '}';
    }

    public StrategyType getStrategyType()
    {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType)
    {
        this.strategyType = strategyType;
    }

    public String getPromptKeyWords()
    {
        return promptKeyWords;
    }

    public void setPromptKeyWords(String promptKeyWords)
    {
        this.promptKeyWords = promptKeyWords;
    }
}
