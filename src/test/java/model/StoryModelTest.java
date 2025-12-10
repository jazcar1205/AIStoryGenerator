package model;

import model.options.Complexity;
import model.options.Length;
import model.options.StrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoryModelTest
{
    StoryModel storyModel;

    @BeforeEach
    void setUp()
    {
        storyModel = new StoryModel();
    }

    @Test
    void setStory()
    {
        storyModel.setStory("Test");
        assertEquals("Test", storyModel.getStory());
    }

    @Test
    void setLength()
    {
        storyModel.setLength(Length.LONG);
        assertEquals(Length.LONG, storyModel.getLength());
    }

    @Test
    void setComplexity()
    {
        storyModel.setComplexity(Complexity.COMPLEX);
        assertEquals(Complexity.COMPLEX, storyModel.getComplexity());
    }

    @Test
    void setStrategy()
    {
        storyModel.setStrategy(StrategyType.SCIFI);
        assertEquals(StrategyType.SCIFI, storyModel.getStrategy());
    }

    @Test
    void getStrategy()
    {
        assertEquals(StrategyType.FANTASY, storyModel.getStrategy());
    }

    @Test
    void getStory()
    {
        assertEquals("", storyModel.getStory());
    }

    @Test
    void getLength()
    {
        assertEquals(Length.MEDIUM, storyModel.getLength());
    }

    @Test
    void getComplexity()
    {
        assertEquals(Complexity.AVERAGE, storyModel.getComplexity());
    }
}