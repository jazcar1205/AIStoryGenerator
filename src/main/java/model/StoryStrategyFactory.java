package model;

import model.options.StrategyType;
import model.strategies.*;
import service.OpenAIService;

/**
 * Returns a certain strategy for the API to use
 * depending on what strategy type was selected by the user. 
 */
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