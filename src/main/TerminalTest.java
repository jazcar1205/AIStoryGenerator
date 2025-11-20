package main;

import controller.MainController;
import model.*;
import persistence.Session;
import persistence.SessionManager;
import service.OpenAIService;

import java.util.Scanner;

public class TerminalTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StoryModel model = new StoryModel();
        model.attach((story, length, complexity) ->
                System.out.println("\n=== Updated Story ===\n"
                        + story
                        + "\nLength: " + length
                        + "\nComplexity: " + complexity + "\n")
        );


        System.out.print("Enter your OpenAI API key: ");
        String apiKey = scanner.nextLine().trim();
        OpenAIService service = new OpenAIService(apiKey);

        MainController controller = new MainController(model);

        Length length = Length.MEDIUM;
        Complexity complexity = Complexity.AVERAGE;
        String genre = "Fantasy";

        // --- Genre selection ---
        while (true) {
            System.out.println("Select Genre: Fantasy [1]  Romance [2]  SciFi [3]  Horror [4]");
            System.out.print("Choice: ");
            String genreChoice = scanner.nextLine().trim();
            switch (genreChoice) {
                case "1" -> genre = "Fantasy";
                case "2" -> genre = "Romance";
                case "3" -> genre = "SciFi";
                case "4" -> genre = "Horror";
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }

        // --- Complexity selection ---
        while (true) {
            System.out.println("Select Complexity: Child Friendly [1]  Average [2]  Complex [3]");
            System.out.print("Choice: ");
            String complexChoice = scanner.nextLine().trim();
            switch (complexChoice) {
                case "1" -> complexity = Complexity.CHILDFRIENDLY;
                case "2" -> complexity = Complexity.AVERAGE;
                case "3" -> complexity = Complexity.DIFFICULT;
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }

        // --- Length selection ---
        while (true) {
            System.out.println("Select Length: Short [1]  Medium [2]  Long [3]");
            System.out.print("Choice: ");
            String lengthChoice = scanner.nextLine().trim();
            switch (lengthChoice) {
                case "1" -> length = Length.SHORT;
                case "2" -> length = Length.MEDIUM;
                case "3" -> length = Length.LONG;
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }

        model.setLength(length);
        model.setComplexity(complexity);

        System.out.println("\nSelected Settings:");
        System.out.println("Genre: " + genre);
        System.out.println("Complexity: " + complexity);
        System.out.println("Length: " + length);

        SessionManager sessionManager = new SessionManager();

        while (true) {
            System.out.println("Options: [1] Generate story  [2] Save session  [3] Load session  [0] Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter prompt: ");
                    String prompt = scanner.nextLine().trim();
                    controller.generateStory(prompt);

                    // Temporary wait for async generation
                    try { Thread.sleep(4000); } catch (InterruptedException ignored) {}
                }
                case "2" -> {
                    System.out.print("Enter filename to save: ");
                    String saveFile = scanner.nextLine().trim();
                    Session session = new Session(model.getStory(), "", genre);
                    sessionManager.saveSession(session, saveFile);
                    System.out.println("Session saved to " + saveFile);
                }
                case "3" -> {
                    System.out.print("Enter filename to load: ");
                    String loadFile = scanner.nextLine().trim();
                    Session loaded = sessionManager.loadSession(loadFile);
                    if (loaded != null) {
                        model.setStory(loaded.getStory());
                        System.out.println("Session loaded from " + loadFile);
                    } else {
                        System.out.println("Failed to load session.");
                    }
                }
                case "0" -> {
                    System.out.println("Exiting.");
                    scanner.close();
                    controller.shutdown(); // shut down executor
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
