package persistence;

import model.Complexity;
import model.Length;
import model.StoryStrategy;
import model.StrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Session;
import persistence.SessionManager;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest
{
    Session session;
    SessionManager sm;
    @BeforeEach
    public void setup()
    {
        session = new Session();
        sm = new SessionManager();
    }

    @Test
    public void saveSessionTest()
    {
        //confirming that not exceptions are thrown.
        assertDoesNotThrow(()->{sm.saveSession(session, "test");});
        //asserting that the file exists.
        File file = new File("save_files/test");
        assertTrue(file.exists());
    }

    @Test
    public void loadSessionTest()
    {
        sm.saveSession(session, "test");
        //making sure that the file exists.
        assertDoesNotThrow(()->{
            Session newSession = sm.loadSession("test");
		assertEquals("Filler text.", newSession.getStory());
            assertEquals(Complexity.CHILDFRIENDLY, newSession.getComplexity());
            assertEquals(Length.SHORT, newSession.getLength());
            assertEquals(StrategyType.FANTASY, newSession.getStoryStrategy());
        });
    }

    @Test
    void getFilePath()
    {
        sm.loadSession("test");
        assertEquals(Path.of("save_files/test"), sm.getFilePath());
    }
}