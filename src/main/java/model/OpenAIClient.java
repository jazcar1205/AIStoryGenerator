package model;

/**
 * Singleton API client
 */

class OpenAIClient
{
    private static OpenAIClient instance = null;
    private OpenAIClient()
    {

    }

    public static synchronized OpenAIClient getInstance()
    {
	  if(instance == null)
	  {
		instance = new OpenAIClient();
	  }
	  return instance;
    }
}