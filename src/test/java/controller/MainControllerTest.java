package controller;

import model.StoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
    void setStrategy()
    {

    }

    @Test
    void generateStory()
    {
        //was able to reveal that there was a slight mismatch in the order of
        //setting the service in controller and passing it to the strategy.
        assertDoesNotThrow(() -> controller.generateStory());
        assertNotNull(controller.getStory());
        assertEquals("Responding to: [Length: Any, Complexity: Any, Setting: Any, Tone: Any, Time Period: Any, Pace: Any, Perspective: Any, Characters: Any, story: ]",
                controller.getStory());
    }
}