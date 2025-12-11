package model;

import model.options.*;
import persistence.Session;
import java.util.Observable;

public class StoryModel extends Observable
{
    private String story;
    private Length len;
    private Complexity complex;
    private Pace pace;
    private Perspective pers;
    private World world;
    private Tone tone;
    private Characters characters;
    private String promptKeyWords;
    private StrategyType strategy;

    public StoryModel() {
        this.story = "";
        this.promptKeyWords = "";
        this.len = Length.ANY;
        this.complex = Complexity.ANY;
        this.strategy = StrategyType.ANY;
        this.pace = Pace.ANY;
        this.pers = Perspective.ANY;
        this.world = new World();
        this.tone = new Tone();
        this.characters = new Characters();
    }
    public StoryModel(String story, Length len, Complexity complex, StrategyType strategy)
    {
        this.story = story;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;
    }

    public StoryModel(String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters)
    {
        this.promptKeyWords = prompt;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;
        this.pace = pace;
        this.pers = pers;
        this.world = new World(setting, timePeriod);
        this.tone = new Tone(tone);
        this.characters = new Characters(characters);
    }

    public StoryModel(String story, String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters)
    {
        this.story = story;
        this.promptKeyWords = prompt;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;
        this.pace = pace;
        this.pers = pers;
        this.world = new World(setting, timePeriod);
        this.tone = new Tone(tone);
        this.characters = new Characters(characters);
    }


    public void setStory(String story) {
        this.story = story;
        setChanged();
        notifyObservers();
    }

    public void setLength(Length len) {
        this.len = len;
        notifyObservers();
    }

    public void setPace(Pace pace) {
        this.pace = pace;
        notifyObservers();
    }

    public void setComplexity(Complexity complex) {
        this.complex = complex;
        notifyObservers();
    }

    public void setPerspective(Perspective pers) {
        this.pers = pers;
        notifyObservers();
    }

    public void setWorld(World world) {
        this.world = world;
        notifyObservers();
    }

    public void setTone(Tone tone) {
        this.tone = tone;
        notifyObservers();
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
        notifyObservers();
    }

    public String getPromptKeyWords()
    {
        return promptKeyWords;
    }

    public void setPromptKeyWords(String promptKeyWords)
    {
        this.promptKeyWords = promptKeyWords;
    }
    public void setStrategy(StrategyType strategy)
    {
        this.strategy = strategy;
    }

    public StrategyType getStrategy()
    {
        return strategy;
    }
    public String getStory() {return story;}
    public Length getLength() { return len; }
    public Complexity getComplexity() { return complex; }
    public Tone getTone() {return tone;}
    public Pace getPace() {return pace;}
    public Perspective getPers() {return pers;}
    public String getTimePeriod() {return world.getTimePeriod();}
    public String getSetting() {return world.getSetting();}
    public Characters getCharacters() {return characters;}

    public Session getAsSession() { return new Session(story, promptKeyWords,complex, len, strategy, pace, pers,world,tone,characters);}
    @Override
    public String toString()
    {
        return "StoryModel{" +
                "story='" + story +
                ", \nprompt= " + promptKeyWords +
                ", \nlen=" + len +
                ", \ncomplex=" + complex +
                ", \nstrategy=" + strategy +
                ", \npace=" + pace +
                ", \nworld =" + world.toString() +
                ", \ntone=" + tone.toString() +
                ", \nperspective=" + pers +
                ", \ncharacters=" + characters.toString();
    }

}
