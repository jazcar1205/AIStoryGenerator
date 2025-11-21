package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Session;
import persistence.SessionManager;

import java.io.File;

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
	  assertDoesNotThrow(()->{sm.loadSession("test");});
    }
}