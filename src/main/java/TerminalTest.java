import controller.MainController;
import model.*;
import model.options.*;
import persistence.Session;
import persistence.SessionManager;
import service.OpenAIService;
import model.strategies.*;

import java.util.Scanner;

public class TerminalTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StoryModel model = new StoryModel();
        model.attach((story, length, complexity,setting,tone, timePeriod,pace,perspective,characters) ->
                System.out.println("\n=== Updated Story ===\n"
                        + story
                        + "\n Settings"
                        + "\nLength: " + length
                        + "\nComplexity: " + complexity + "\n"
                        + "\nSetting: " +setting +"\n"
                        + "\nTone: " +tone+ "\n"
                        + "\nTime Period: " +timePeriod +"\n"
                        + "\nPace: " + pace + "\n"
                        + "\nPerspective: " + perspective + "\n"
                        + "\nCharacters: " + characters + "\n"
        ));

        OpenAIService service = OpenAIService.getInstance();
        MainController controller = new MainController(model,service);
        World world = new World();
        Tone style = new Tone();
        Characters characters = new Characters();

        /*Basic*/
        Length length = Length.MEDIUM;
        Complexity complexity = Complexity.AVERAGE;
        StrategyType genre = StrategyType.FANTASY;

        /* Tone */
        Pace pace = Pace.NORMAL;
        Perspective perspective = Perspective.THIRD;

        // --- Genre selection ---
        while (true) {
            System.out.println("Select Genre: Fantasy [1]  Romance [2]  SciFi [3]  Horror [4]");
            System.out.print("Choice: ");
            String genreChoice = scanner.nextLine().trim();
            switch (genreChoice) {
                case "1" -> genre = StrategyType.FANTASY;
                case "2" -> genre = StrategyType.ROMANCE;
                case "3" -> genre = StrategyType.SCIFI;
                case "4" -> genre = StrategyType.HORROR;
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }

        StoryStrategy selectedStrategy = StoryStrategyFactory.getStrategy(genre, service);
        controller.setStrategy(selectedStrategy);

        // --- Complexity selection ---
        while (true) {
            System.out.println("Select Complexity: Child Friendly [1]  Average [2]  Complex [3]");
            System.out.print("Choice: ");
            String complexChoice = scanner.nextLine().trim();
            switch (complexChoice) {
                case "1" -> complexity = Complexity.CHILDFRIENDLY;
                case "2" -> complexity = Complexity.AVERAGE;
                case "3" -> complexity = Complexity.COMPLEX;
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

        // --- Tone selection ---
        System.out.println("Enter the tone for the story:");
        String tone = scanner.nextLine();
        if(tone != null){ tone = "Any"; }
        style.setTone(tone);

        while (true) {
            System.out.println("Select Pace: [1] Slow [2] Normal [3]Fast");
            System.out.print("Choice: ");
            String complexChoice = scanner.nextLine().trim();
            switch (complexChoice) {
                case "1" -> pace  = Pace.SLOW;
                case "2" -> pace  = Pace.NORMAL;
                case "3" -> pace  = Pace.FAST;
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }


        while (true) {
            System.out.println("Select Perspective : [1] 1st [2] 3rd");
            System.out.print("Choice: ");
            String complexChoice = scanner.nextLine().trim();
            switch (complexChoice) {
                case "1" -> perspective  = Perspective.FIRST;
                case "2" -> perspective  = Perspective.THIRD;
                default -> { System.out.println("Invalid option, try again."); continue; }
            }
            break;
        }

        // --- World selection ---
        System.out.println("Enter desired Setting, To skip just press enter: ");
        String setting = scanner.nextLine();
        if(setting != null){ setting = "Any"; }
        world.setSetting(setting);

        System.out.println("Enter desired Time Period, To skip just press enter: ");
        String time = scanner.nextLine();
        if(time != null){ time = "Any";}
        world.setTimePeriod(time);

        // --- Characters selection ---
        System.out.println("Enter desired Characters, To skip just press enter: ");
        String people = scanner.nextLine();
        if(people != null){ people = "Any";}
        characters.setCharacters(people);




        model.setLength(length);
        model.setComplexity(complexity);
        model.setWorld(world);
        model.setTone(style);
        model.setPace(pace);
        model.setPerspective(perspective);
        model.setCharacters(characters.getCharacters());

        System.out.println("\n--------------------------------------------------------------------\n");

        System.out.println("\nSelected Settings:");
        System.out.println("Genre: " + genre);
        System.out.println("Complexity: " + complexity);
        System.out.println("Length: " + length);

        System.out.println("Pace: " + pace);
        System.out.println("Perspective: " + perspective);
        System.out.println("Tone: " + style.getTone());
        System.out.println("Characters: " + characters.getCharacters());
        System.out.println("Time Period: " + world.getTimePeriod());
        System.out.println("Setting: " + world.getSetting());

        System.out.println("\n--------------------------------------------------------------------\n");

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
                    System.out.println("Generating......");
                    // Temporary wait for async generation
                    try { Thread.sleep(6000); } catch (InterruptedException ignored) {}
                }
                case "2" -> {
                    System.out.print("Enter filename to save: ");
                    String saveFile = scanner.nextLine().trim();
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
