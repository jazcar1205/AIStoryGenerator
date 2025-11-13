package model;

public class RomanceStrategy implements StoryStrategy
{
    @Override
    public String generateStory(String prompt) {
        // later integrate API
        return "Romance story: " + prompt + " with love and heartbreak.";
    }
}
