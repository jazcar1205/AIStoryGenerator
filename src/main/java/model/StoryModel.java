package model;

import persistence.Session;

import java.util.ArrayList;
import java.util.List;

public class StoryModel {
    private String story;
    private Length len;
    private Complexity complex;
    private Pace pace;
    private Perspective pers;
    private String timePeriod;
    private String setting;
    private String tone;
    private String characters;

    private StrategyType strategy;
    private final List<Observer> observers = new ArrayList<>();

    public StoryModel() {
        this.story = "";
        this.len = Length.MEDIUM;
        this.complex = Complexity.AVERAGE;
        this.strategy = StrategyType.FANTASY;
        this.pace = Pace.NORMAL;
        this.pers = Perspective.THIRD;
        setting = "Any";
        tone = "Any";
        timePeriod ="Any";
        this.characters = null;
    }

    public StoryModel(Length len, Complexity complex, StrategyType strategy)
    {
        this.story = "";
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;
    }
    public StoryModel(String story, Length len, Complexity complex, StrategyType strategy)
    {
        this.story = story;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;
    }

    public StoryModel(String story, Length len, Complexity complex, StrategyType strategy,World world, Tone tone,Pace pace, Perspective pers, String characters)
    {
        this.story = story;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;

        this.pace = pace;
        this.pers = pers;
        setting = world.getSetting();
        this.tone = tone.getTone();
        timePeriod = world.getTimePeriod();
        this.characters = characters;
    }

    public StoryModel(String story, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters)
    {
        this.story = story;
        this.len = len;
        this.complex = complex;
        this.strategy = strategy;

        this.pace = pace;
        this.pers = pers;
        this.setting = setting;
        this.tone = tone;
        this.timePeriod = timePeriod;
        this.characters = characters;
    }


    public void setStory(String story) {
        this.story = story;
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
        timePeriod = world.getTimePeriod();
        setting = world.getSetting();
        notifyObservers();
    }

    public void setTone(Tone tone) {
        this.tone = tone.getTone();
        notifyObservers();
    }

    public void setCharacters(String characters) {
        this.characters = characters;
        notifyObservers();
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
    public String getTone() {return tone;}
    public Pace getPace() {return pace;}
    public Perspective getPers() {return pers;}
    public String getTimePeriod() {return timePeriod;}
    public String getSetting() {return setting;}
    public String getCharacters() {return characters;}

    public Session getAsSession() { return new Session(story, complex, len, strategy, pace, pers, timePeriod,tone,setting, characters);}

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) o.update(story, len, complex,setting,tone,timePeriod,pace,pers,characters);
    }

    public interface Observer {
        void update(String story, Length len, Complexity complex,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters );
    }

    @Override
    public String toString()
    {
        return "StoryModel{" +
                "story='" + story + '\'' +
                ", len=" + len +
                ", complex=" + complex +
                ", strategy=" + strategy +
                ", pace=" + pace +
                ", time period=" + timePeriod +
                ", tone=" + tone +
                ", perspective=" + pers +
                ", setting=" + setting +
                ", characters=" + characters +
                ", observers=" + observers +
                '}';
    }

}
