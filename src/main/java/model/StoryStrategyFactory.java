package model;

import service.OpenAIService;

public class StoryStrategyFactory {
    public static StoryStrategy getStrategy(String genre, OpenAIService service) {
        return switch (genre) {
            case "Fantasy" -> new FantasyStrategy(service);
            case "Romance" -> new RomanceStrategy(service);
            case "SciFi" -> new SciFiStrategy(service);
            case "Horror" -> new HorrorStrategy(service);
            default -> new GenericStrategy(service);
        };
    }
}