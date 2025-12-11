package controller;

import model.StoryModel;
import model.options.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Session;
import service.OpenAIService;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest
{

    StoryModel model;
    OpenAIService mockService;
    MainController controller;

    @BeforeEach
    void setUp() throws Exception
    {
	  model = new StoryModel();
        mockService= mock(OpenAIService.class);
        // 2. Define the dynamic behavior using thenAnswer()
        when(mockService.generateText(anyString(), anyDouble(), anyInt()))
                .thenAnswer(invocation -> {
                    //getting argument so that we know what we are actually responding to.
                    String prompt = (String) invocation.getArgument(0);
                    return "Responding to: [" + prompt + "]";
                });
        controller = new MainController(model, mockService);
    }

    @Test
    void setStrategyTest()
    {
        controller.setStrategy(StrategyType.FANTASY);
        Session session = controller.getSession();
        assertEquals(StrategyType.FANTASY, session.getStrategyType());
        controller.generateStory();
        assertEquals("Responding to: [Length: Any, Complexity: Any, Setting: Any, Tone: Any, Time Period: Any, Pace: Any, Perspective: Any, Characters: Any, fantasy story: ]",
                controller.getStory());
    }

    @Test
    void updateModelPassModelTest()
    {
        //String story, String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters
        StoryModel model = new StoryModel("Test", "prompt", Length.SHORT, Complexity.CHILDFRIENDLY,
                StrategyType.FANTASY, "setting", "tone", "time period", Pace.FAST,
                Perspective.FIRST, "characters");

        controller.updateModel(model);
        assertNotEquals("Test", controller.getStory());
        Session session = controller.getSession();
        assertEquals("prompt", session.getPromptKeyWords());
        assertEquals(Length.SHORT, session.getLength());
        assertEquals(Complexity.CHILDFRIENDLY, session.getComplexity());
        assertEquals(StrategyType.FANTASY, session.getStrategyType());
        assertEquals("setting", session.getSetting());
        assertEquals("tone", session.getTone());
        assertEquals("time period", session.getTimePeriod());
        assertEquals(Pace.FAST, session.getPace());
        assertEquals(Perspective.FIRST, session.getPers());
        assertEquals("characters", session.getCharacters());
    }

    @Test
    void generateStoryTest()
    {
        //was able to reveal that there was a slight mismatch in the order of
        //setting the service in controller and passing it to the strategy.
        assertDoesNotThrow(() -> controller.generateStory());
        assertNotNull(controller.getStory());
        assertEquals("Responding to: [Length: Any, Complexity: Any, Setting: Any, Tone: Any, Time Period: Any, Pace: Any, Perspective: Any, Characters: Any, story: ]",
                controller.getStory());
    }
}