package main;

import controller.MainController;
import model.StoryModel;
import model.FantasyStrategy;
import service.OpenAIService;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Initialize model
        StoryModel model = new StoryModel();

        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("FATAL ERROR: OPENAI_API_KEY environment variable not set.");
            // handle with a dialog box in app
            System.exit(1);
        }

        OpenAIService service = new OpenAIService(System.getenv("OPENAI_API_KEY"));

        MainController controller = new MainController(model);

        controller.setStrategy(new FantasyStrategy(service));

        new MainFrame(model, controller);
    }
}
