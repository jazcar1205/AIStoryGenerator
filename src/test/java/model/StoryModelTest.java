package model;

import model.options.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Session;

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
        assertEquals(StrategyType.ANY, storyModel.getStrategy());
    }

    @Test
    void getStory()
    {
        assertEquals("", storyModel.getStory());
    }

    @Test
    void getLength()
    {
        assertEquals(Length.ANY, storyModel.getLength());
    }

    @Test
    void getComplexity()
    {
        assertEquals(Complexity.ANY, storyModel.getComplexity());
    }

    @Test
    void setPace()
    {
        storyModel.setPace(Pace.FAST);
        assertEquals(Pace.FAST, storyModel.getPace());
    }

    @Test
    void setPerspective()
    {
        storyModel.setPerspective(Perspective.THIRD);
        assertEquals(Perspective.THIRD, storyModel.getPers());
    }

    @Test
    void setWorld()
    {
        storyModel.setWorld(new World("England", "12th century"));
        assertEquals("England", storyModel.getSetting());
        assertEquals("12th century", storyModel.getTimePeriod());
    }

    @Test
    void setTone()
    {
        storyModel.setTone(new Tone("Test"));
        assertEquals("Test", storyModel.getTone().getTone());
    }

    @Test
    void setCharacters()
    {
        storyModel.setCharacters(new Characters("Test"));
        assertEquals("Test", storyModel.getCharacters().getCharacters());
    }

    @Test
    void getPromptKeyWords()
    {
        assertEquals("", storyModel.getPromptKeyWords());
    }

    @Test
    void setPromptKeyWords()
    {
        storyModel.setPromptKeyWords("Test");
        assertEquals("Test", storyModel.getPromptKeyWords());
    }

    @Test
    void getTone()
    {
        assertEquals("Any", storyModel.getTone().getTone());
    }

    @Test
    void getPace()
    {
        assertEquals(Pace.ANY, storyModel.getPace());
    }

    @Test
    void getPers()
    {
        assertEquals(Perspective.ANY, storyModel.getPers());
    }

    @Test
    void getTimePeriod()
    {
        assertEquals("Any", storyModel.getTimePeriod());
    }

    @Test
    void getSetting()
    {
        assertEquals("Any", storyModel.getSetting());
    }

    @Test
    void getCharacters()
    {
        assertEquals("Any", storyModel.getCharacters().getCharacters());
    }

    @Test
    void getAsSession()
    {
        Session session = storyModel.getAsSession();
        assertEquals(session.getStory(), storyModel.getStory());
        assertEquals(session.getLength(), storyModel.getLength());
        assertEquals(session.getComplexity(), storyModel.getComplexity());
        assertEquals(session.getPromptKeyWords(), storyModel.getPromptKeyWords());
        assertEquals(session.getTone(), storyModel.getTone().getTone());
        assertEquals(session.getCharacters(), storyModel.getCharacters().getCharacters());
        assertEquals(session.getPace(), storyModel.getPace());
        assertEquals(session.getPers(), storyModel.getPers());
        assertEquals(session.getTimePeriod(), storyModel.getTimePeriod());
        assertEquals(session.getSetting(), storyModel.getSetting());
    }
}