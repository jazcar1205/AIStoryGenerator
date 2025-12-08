package controller;

import service.OpenAIService;

public class ScriptedAPIService extends OpenAIService
{
    public ScriptedAPIService(String apikey)
    {
	  super(apikey);
    }

    public String generateText(String prompt)
    {
	  return "Response to: " + prompt;
    }
}
