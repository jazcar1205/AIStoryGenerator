package model;

public class HorrorStrategy implements StoryStrategy
{
    @Override
    public String generateStory(String prompt) {
        // later integrate API
        return "Horror story: " + prompt + " with darkness and scares.";
    }
}
