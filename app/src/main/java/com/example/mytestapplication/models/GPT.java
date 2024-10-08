package com.example.mytestapplication.models;

import androidx.annotation.NonNull;
import android.util.Log;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GPT {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // Define an interface for callback
    public interface ResponseCallback {
        void onResponse(String response);
    }

    // Instance of the callback
    private ResponseCallback responseCallback;

    // Method to set the callback
    public void setResponseCallback(ResponseCallback callback) {
        this.responseCallback = callback;
    }

    public void callAPI(String question) {

        OkHttpClient client = new OkHttpClient();
        String TAG = "JsonExample";

        // Create JSON object to represent the API body
        JSONObject jsonBody = new JSONObject();
        try {
            // Log the question for debugging
            Log.d(TAG, question);

            // Set the model name
            jsonBody.put("model", "gpt-4o-mini");

            // Create a JSON array to hold the messages
            JSONArray messagesArray = new JSONArray();

            // Create a JSON object for the user message
            JSONObject messageObject = new JSONObject();
            messageObject.put("role", "user");
            messageObject.put("content", question);

            // Add the message to the array
            messagesArray.put(messageObject);

            // Add the messages array to the main JSON body
            jsonBody.put("messages", messagesArray);

            // Set the temperature value
            jsonBody.put("temperature", 0.7);

            // Log the final JSON object
            Log.d(TAG, jsonBody.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the request body
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);

        // Build the request
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer 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")  // Replace with your actual API key
                .header("Content-Type", "application/json")  // Set Content-Type to application/json
                .post(body)
                .build();

        // Log the request details for debugging
        Log.d(TAG, request.toString());

        // Asynchronous API call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // Handle failure
                if (responseCallback != null) {
                    responseCallback.onResponse("Failed to load response due to: " + e.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body() != null ? response.body().string() : null;
                    if (responseBody != null) {
                        Log.d("RawResponse", responseBody);  // Log the raw response for debugging

                        // Add this check to see if the response is a plain string
                        if (responseBody.startsWith("{")) {
                            try {
                                JSONObject jsonObject = new JSONObject(responseBody);
                                JSONArray choicesArray = jsonObject.getJSONArray("choices");

                                // Get the content of the first choice (the response from the AI)
                                String result = choicesArray.getJSONObject(0)
                                        .getJSONObject("message")
                                        .getString("content");

                                // Trigger the callback with the response
                                responseCallback.onResponse(result.trim());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                responseCallback.onResponse("Failed to parse the response.");
                            }
                        } else {
                            // Handle if it's a plain string or invalid JSON
                            responseCallback.onResponse("Unexpected response format: " + responseBody);
                        }
                    } else {
                        responseCallback.onResponse("Empty response from the server.");
                    }
                } else {
                    responseCallback.onResponse("Failed to load response due to: " + response.body().string());
                }
            }

        });
    }
}
