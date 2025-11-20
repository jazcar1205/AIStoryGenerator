package model;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Singleton OpenAI client
 */
public class APIClient {
    private static APIClient instance = null;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final OkHttpClient client;
    private final String apiKey;

    private APIClient() {
        client = new OkHttpClient();
        apiKey = System.getenv("OPENAI_API_KEY");
    }

    public static synchronized APIClient getInstance() {
        if(instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public String generateStory(String prompt) throws IOException {
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo"); // or gpt-4
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);

        RequestBody body = RequestBody.create(
                requestBody.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API Error: " + response.code() + " - " + response.message());
            }
            String respBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(respBody);
            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        }
    }

    public void generateStoryAsync(String prompt,
                                   java.util.function.Consumer<String> onSuccess,
                                   java.util.function.Consumer<Exception> onError) {
        new Thread(() -> {
            try {
                String story = generateStory(prompt);
                onSuccess.accept(story);
            } catch (Exception e) {
                onError.accept(e);
            }
        }).start();
    }
}
