import controller.MainController;
import model.FantasyStrategy;
import model.StoryModel;
import persistence.Session;
import persistence.SessionManager;
import service.OpenAIService;

import java.util.Scanner;

public class TerminalTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. Initialize model
        StoryModel model = new StoryModel();
        model.attach(() -> System.out.println("\n=== Updated Story ===\n" + model.getStory() + "\n"));

        // 2. Initialize OpenAI service
        System.out.print("Enter your OpenAI API key: ");
        String apiKey = scanner.nextLine().trim();
        OpenAIService service = new OpenAIService(apiKey);

        // 3. Initialize controller
        MainController controller = new MainController(model);

        // 4. Set a story strategy (Fantasy example)
        controller.setStrategy(new FantasyStrategy(service));

        // 5. Session manager for save/load
        SessionManager sessionManager = new SessionManager();

        // 6. Main loop
        while (true) {
            System.out.println("Options: [1] Generate story  [2] Save session  [3] Load session  [0] Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter prompt: ");
                    String prompt = scanner.nextLine().trim();
                    System.out.println("Generating story...\n");
                    controller.generateStory(prompt);

                    // Wait for async generation
                    try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
                    break;

                case "2":
                    System.out.print("Enter filename to save: ");
                    String saveFile = scanner.nextLine().trim();
                    Session session = new Session(model.getStory(), "", "Fantasy");
                    sessionManager.saveSession(session, saveFile);
                    System.out.println("Session saved to " + saveFile);
                    break;

                case "3":
                    System.out.print("Enter filename to load: ");
                    String loadFile = scanner.nextLine().trim();
                    Session loaded = sessionManager.loadSession(loadFile);
                    if (loaded != null) {
                        model.setStory(loaded.getStory());
                        System.out.println("Session loaded from " + loadFile);
                    } else {
                        System.out.println("Failed to load session.");
                    }
                    break;

                case "0":
                    System.out.println("Exiting.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
