package controller;

import model.StoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.OpenAIService;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest
{

    StoryModel model;
    OpenAIService scriptedAPIService;
    MainController controller;

    @BeforeEach
    void setUp()
    {
	  model = new StoryModel();
	  scriptedAPIService = new ScriptedAPIService("test");
	  controller = new MainController(model, scriptedAPIService);
    }

    @Test
    void setStrategy()
    {

    }

    @Test
    void generateStory()
    {
    }
}