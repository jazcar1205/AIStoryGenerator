package model;

import model.options.StrategyType;
import model.strategies.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.OpenAIService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StoryStrategyFactoryTest
{
    OpenAIService mockService;
    @BeforeEach
    void setUp() throws Exception
    {
        mockService = mock(OpenAIService.class);
        // 2. Define the dynamic behavior using thenAnswer()
        when(mockService.generateText(anyString(), anyDouble(), anyInt()))
                .thenAnswer(invocation -> {
                    //getting argument so that we know what we are actually responding to.
                    String prompt = (String) invocation.getArgument(0);
                    return "Responding to: [" + prompt + "]";
                });
    }
    @Test
    void getStrategy()
    {
        assertInstanceOf(FantasyStrategy.class, StoryStrategyFactory.getStrategy(StrategyType.FANTASY, mockService));
        assertInstanceOf(HorrorStrategy.class, StoryStrategyFactory.getStrategy(StrategyType.HORROR, mockService));
        assertInstanceOf(RomanceStrategy.class, StoryStrategyFactory.getStrategy(StrategyType.ROMANCE, mockService));
        assertInstanceOf(SciFiStrategy.class, StoryStrategyFactory.getStrategy(StrategyType.SCIFI, mockService));
        assertInstanceOf(GenericStrategy.class, StoryStrategyFactory.getStrategy(StrategyType.ANY, mockService));
    }
}