package service;
public class OpenAIService {
    private final String API_KEY;
    private final String MODEL = "gpt-3.5-turbo"; // or "gpt-4"

    public OpenAIService(String apiKey)
    {
	  API_KEY = apiKey;
    }

    public String generateText(String prompt, double temperature, int maxTokens) {
        // HTTP request to OpenAI API
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("messages", createMessages(prompt));
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);

        // Send request and handle response
        return sendRequest(requestBody);
    }
}