package model;

import java.util.ArrayList;
import java.util.List;

public class StoryModel {
    private String story;
    private final List<Observer> observers = new ArrayList<>();

    public void setStory(String story) {
        this.story = story;
        notifyObservers();
    }

    public String getStory() {
        return story;
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) o.update();
    }

    public interface Observer {
        void update();
    }
}
