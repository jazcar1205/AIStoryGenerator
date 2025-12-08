import controller.MainController;
import model.StoryModel;
import model.FantasyStrategy;
import persistence.ConfigLoader;
import service.OpenAIService;
import view.MainFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Initialize model
        StoryModel model = new StoryModel();
        OpenAIService service = new OpenAIService(ConfigLoader.getKey("OPENAI_API_KEY"));
        MainController controller = new MainController(model, service);
        controller.setStrategy(new FantasyStrategy(service));
        new MainFrame(model, controller);
    }
}
