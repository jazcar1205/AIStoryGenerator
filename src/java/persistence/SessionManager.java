package persistence;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {

    private static final Logger logger = Logger.getLogger(SessionManager.class.getName());

    public void saveSession(Session session, String filename) {
        try {
            JSONObject json = new JSONObject();
            json.put("story", session.getStory());
            json.put("prompt", session.getPrompt());
            json.put("strategyName", session.getStrategyName());

            Files.writeString(Path.of(filename), json.toString(4));
            logger.info("Session saved to " + filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save session to " + filename, e);
        }
    }

    public Session loadSession(String filename) {
        try {
            String content = Files.readString(Path.of(filename));
            JSONObject json = new JSONObject(content);

            Session session = new Session();
            session.setStory(json.optString("story"));
            session.setPrompt(json.optString("prompt"));
            session.setStrategyName(json.optString("strategyName"));

            logger.info("Session loaded from " + filename);
            return session;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load session from " + filename, e);
            return null;
        }
    }
}
