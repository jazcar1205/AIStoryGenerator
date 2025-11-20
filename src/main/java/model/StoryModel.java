package model;

import java.util.ArrayList;
import java.util.List;

public class StoryModel {
    private String story;
    private Length len;
    private Complexity complex;
    private final List<Observer> observers = new ArrayList<>();

    public StoryModel() {
        this.story = "";
        this.len = Length.MEDIUM;
        this.complex = Complexity.AVERAGE;
    }

    public void setStory(String story) {
        this.story = story;
        notifyObservers();
    }

    public void setLength(Length len) {
        this.len = len;
        notifyObservers();
    }

    public void setComplexity(Complexity complex) {
        this.complex = complex;
        notifyObservers();
    }

    public String getStory() {
        return story;
    }
    public Length getLength() { return len; }
    public Complexity getComplexity() { return complex; }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) o.update(story, len, complex);
    }

    public interface Observer {
        void update(String story, Length len, Complexity complex);
    }
}
