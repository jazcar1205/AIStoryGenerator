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

        OpenAIService service = new OpenAIService(System.getenv("OPENAI_API_KEY"));

        MainController controller = new MainController(model);

        controller.setStrategy(new FantasyStrategy(service));

        // Initialize view
        //new MainFrame(model, controller);
        new MainFrame();
    }
}
