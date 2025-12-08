import controller.MainController;
import model.StoryModel;
import model.FantasyStrategy;
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

        Properties prop = new Properties();
        String fileName = "src/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        OpenAIService service = new OpenAIService(prop.getProperty("OPENAI_API_KEY"));

        MainController controller = new MainController(model, service);

        controller.setStrategy(new FantasyStrategy(service));

        new MainFrame(model, controller);
    }
}
