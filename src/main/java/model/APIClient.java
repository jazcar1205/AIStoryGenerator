package model;

import okhttp3.*;
import org.json.JSONObject;
import persistence.ConfigLoader;
import java.io.IOException;

/**
 * Singleton OpenAI client
 * User of the API
 * Sends the requests
* */

public class APIClient {
    private static APIClient instance = null;
    private static final String API_URL = ConfigLoader.getKey("API_URL");
    private final OkHttpClient client;
    private final String apiKey;

    private APIClient() {
        client = new OkHttpClient();
        apiKey = ConfigLoader.getKey("OPENAI_API_KEY");
    }

    public APIClient(String apiKey) {
        client = new OkHttpClient();
        this.apiKey = apiKey;
    }

    public static synchronized APIClient getInstance() {
        if(instance == null) {
            instance = new APIClient();
        }
        return instance;
    }
    public JSONObject postChat(JSONObject requestBody) throws IOException {
        RequestBody body = RequestBody.create(
                requestBody.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try(Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                throw new IOException("OpenAI API Error: " + response.code());
            }

            String respBody = response.body().string();
            return new JSONObject(respBody);
        }
    }
}

