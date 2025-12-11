package service;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import model.APIClient;
import java.util.function.Consumer;

/**
 * OpenAI Service
 * provider of the API
 * receives requests
 * sends back responses
 * */

public class OpenAIService {

    private static OpenAIService instance;
    private final APIClient apiClient;

    private OpenAIService() {
        this.apiClient = APIClient.getInstance();
    }

    public OpenAIService(String apiKey) {
        this.apiClient = new APIClient(apiKey);
    }

    /**
     * Ensures that the service is a singleton. Only one service can exist.
     * @return
     */
    public static synchronized OpenAIService getInstance() {
        if(instance == null) instance = new OpenAIService();
        return instance;
    }

    /**
     * Uses the client to send a request to the API
     * and returns the request provided.
     * @param prompt
     * @param temperature
     * @param maxTokens
     * @return
     * @throws Exception
     */
    public String generateText(String prompt, double temperature, int maxTokens) throws Exception {

        JSONObject message = new JSONObject()
                .put("role", "user")
                .put("content", prompt);

        JSONArray messages = new JSONArray().put(message);

        JSONObject requestBody = new JSONObject()
                .put("model", "gpt-3.5-turbo")
                .put("messages", messages)
                .put("temperature", temperature)
                .put("max_tokens", maxTokens);


        JSONObject response = apiClient.postChat(requestBody);

        return response.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }

    /**
     * For asyncronous generation.
     * @param prompt
     * @param onSuccess
     * @param onError
     */
    public void generateTextAsync(String prompt, Consumer<String> onSuccess, Consumer<Exception> onError) {
        new Thread(() -> {
            try {
                String story = generateText(prompt, 0.7, 500);
                onSuccess.accept(story);
            } catch (Exception e) {
                onError.accept(e);
            }
        }).start();
    }
}
