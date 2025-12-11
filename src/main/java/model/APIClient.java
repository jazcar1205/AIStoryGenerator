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

    private Call activeCall;
    /**
     * Automatically loads key from config.properties.
     */
    private APIClient() {
        client = new OkHttpClient();
        apiKey = ConfigLoader.getKey("OPENAI_API_KEY");
    }

    /**
     *
     * @param apiKey Open AI API key.
     */
    public APIClient(String apiKey) {
        client = new OkHttpClient();
        this.apiKey = apiKey;
    }

    /**
     * Ensures client is a singleton
     * Only one client can exist at a time.
     * @return
     */
    public static synchronized APIClient getInstance() {
        if(instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    /**
     * Allows UI and service to cancel the Api request
     */
    public void cancel(){
        System.out.println("Active Call is "+activeCall);
        if (activeCall != null && ! activeCall.isCanceled()){
            System.out.println("Active Call is canceling");
            activeCall.cancel();
        }
    }


    /**
     * Send request to API.
     * @param requestBody What to send to the API
     * @return
     * @throws IOException
     */
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

        activeCall = client.newCall(request);

        try(Response response = activeCall.execute()) {
            if(!response.isSuccessful()) {
                throw new IOException("OpenAI API Error: " + response.code());
            }

            String respBody = response.body().string();
            return new JSONObject(respBody);
        }
    }
}

