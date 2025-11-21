package persistence;

import model.Complexity;
import model.Length;
import model.StrategyType;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages saving and loading sessions.
 */
public class SessionManager {

    private static final Logger logger = Logger.getLogger(SessionManager.class.getName());
    private Path filePath;

    /**
     * Helps to save the session as a JSON object according to the filepath.
     * @param session
     * @param filename
     */
    public void saveSession(Session session, String filename) {
        this.filePath = Path.of("save_files/", filename);
        try {
            JSONObject json = new JSONObject();
            json.put("story", session.getStory());
            json.put("complexity", session.getComplexity().toString());
            json.put("length", session.getLength().toString());
            json.put("strategy", session.getStoryStrategy().toString());
            //System.out.println(filePath);
            Files.writeString(filePath, json.toString(4));
            logger.info("Session saved to " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save session to " + filePath.toString(), e);
        }
    }

    /**
     * Helps to load the session into a session object from a JSON file.
     * @param filename
     * @return
     */
    public Session loadSession(String filename) {
        this.filePath = Path.of("save_files/", filename);
        try {
            String content = Files.readString(this.filePath);
            //System.out.println("Content: " + content);
            JSONObject json = new JSONObject(content);

            Session session = new Session();
            session.setStory(json.optString("story"));
            session.setComplexity(json.optEnum(Complexity.class, "complexity"));
            session.setLength(json.optEnum(Length.class, "length"));
            session.setStoryStrategy(json.optEnum(StrategyType.class, "strategy"));

            logger.info("Session loaded from " + filePath);
            System.out.println("Loaded session: " + session);
            return session;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load session from " + filePath, e);
            return null;
        }
    }

    public Path getFilePath()
    {
        return filePath;
    }
}
