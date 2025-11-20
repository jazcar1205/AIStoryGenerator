package model;
public class SciFiStrategy implements StoryStrategy
{
    @Override
    public String generateStory(String prompt) {
        return "Sci-Fi story: " + prompt + " in a galaxy far, far away.";
    }
}