package service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;
public class OpenAIService
{
    private final String API_KEY;
    private final String MODEL = "gpt-3.5-turbo"; // or "gpt-4"
    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final HttpClient client;

    public OpenAIService(String apiKey)
    {
        API_KEY = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    public String generateText(String prompt) throws Exception
    {
        // Build request body
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        // Send request
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
                                                   );

        // Parse response
        if (response.statusCode() == 200)
        {
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } else
        {
            throw new RuntimeException("API Error: " + response.body());
        }
    }

    public String generateText(String prompt, double temperature, int maxTokens)
    {
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