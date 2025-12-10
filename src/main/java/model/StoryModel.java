package model;

import persistence.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class StoryModel extends Observable
{
    private String story;
    private Length len;
    private Complexity complex;
    private StrategyType strategy;

    public StoryModel()
    {
        this.story = "";
        this.len = Length.MEDIUM;
        this.complex = Complexity.AVERAGE;
        this.strategy = StrategyType.FANTASY;
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

    public void setStory(String story)
    {
        this.story = story;
        setChanged();
        notifyObservers();
    }

    public void setLength(Length len)
    {
        this.len = len;
        notifyObservers();
    }

    public void setComplexity(Complexity complex)
    {
        this.complex = complex;
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

    public String getStory()
    {
        return story;
    }

    public Length getLength()
    {
        return len;
    }

    public Complexity getComplexity()
    {
        return complex;
    }

    public Session getAsSession()
    {
        return new Session(story, complex, len, strategy);
    }

    @Override
    public String toString()
    {
        return "StoryModel{" +
                "story='" + story + '\'' +
                ", len=" + len +
                ", complex=" + complex +
                ", strategy=" + strategy;
    }

}
