package persistence;

import model.StoryModel;
import model.options.Complexity;
import model.options.Length;
import model.options.StrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest
{
    Session session;
    @BeforeEach
    void setUp()
    {
	  session = new Session("Filler text", Complexity.CHILDFRIENDLY, Length.SHORT, StrategyType.FANTASY);
    }

    @Test
    void getStory()
    {
	  assertEquals("Filler text", session.getStory());
    }

    @Test
    void setStory()
    {
	  session.setStory("New text");
	  assertEquals("New text", session.getStory());
    }

    @Test
    void getComplexity()
    {
	  assertEquals(Complexity.CHILDFRIENDLY, session.getComplexity());
    }

    @Test
    void setComplexity()
    {
	  session.setComplexity(Complexity.COMPLEX);
	  assertEquals(Complexity.COMPLEX, session.getComplexity());
    }

    @Test
    void getLength()
    {
	  assertEquals(Length.SHORT, session.getLength());
    }

    @Test
    void setLength()
    {
	  session.setLength(Length.LONG);
	  assertEquals(Length.LONG, session.getLength());
    }

    @Test
    void getStoryStrategy()
    {
	  assertEquals(StrategyType.FANTASY, session.getStoryStrategy());
    }

    @Test
    void setStoryStrategy()
    {
	  session.setStoryStrategy(StrategyType.FANTASY);
    }

    @Test
    void getAsModel()
    {
	  StoryModel model = session.getAsModel();
	  assertNotNull(model);
	  assertEquals("Filler text", model.getStory());
	  assertEquals(Complexity.CHILDFRIENDLY, model.getComplexity());
	  assertEquals(Length.SHORT, model.getLength());
	  assertEquals(StrategyType.FANTASY, model.getStrategy());
    }
}