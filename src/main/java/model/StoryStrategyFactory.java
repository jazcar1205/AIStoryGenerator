package model;

import model.strategies.*;
import service.OpenAIService;

public class StoryStrategyFactory {
    public static StoryStrategy getStrategy(StrategyType genre, OpenAIService service) {
        return switch (genre) {
            case StrategyType.FANTASY -> new FantasyStrategy(service);
            case StrategyType.ROMANCE -> new RomanceStrategy(service);
            case StrategyType.SCIFI -> new SciFiStrategy(service);
            case StrategyType.HORROR -> new HorrorStrategy(service);
            default -> new GenericStrategy(service);
        };
    }
}