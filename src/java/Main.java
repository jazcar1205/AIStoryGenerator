import controller.MainController;
import model.StoryModel;
import model.FantasyStrategy;
import service.OpenAIService;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Initialize model
        StoryModel model = new StoryModel();

        // Initialize OpenAI service (read key from env or config)
        OpenAIService service = new OpenAIService(System.getenv("OPENAI_API_KEY"));

        // Initialize controller
        MainController controller = new MainController(model);

        // Set a strategy (Fantasy in this example)
        controller.setStrategy(new FantasyStrategy(service));

        // Initialize view
        new MainFrame(model, controller);
    }
}
