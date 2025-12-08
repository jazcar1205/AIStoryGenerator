package service;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.ConfigLoader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

/**
 * Handles OpenAI API requests.
 */
public class OpenAIService {
    private final String API_KEY;
    private final String MODEL = "gpt-3.5-turbo";
    private final String API_URL = ConfigLoader.getKey("API_URL");
    private final HttpClient client;

    public OpenAIService(String apiKey) {
        this.API_KEY = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    public String generateText(String prompt, double temperature, int maxTokens) throws Exception {
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("messages", messages);
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } else {
            throw new RuntimeException("API Error: " + response.body());
        }
    }

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
