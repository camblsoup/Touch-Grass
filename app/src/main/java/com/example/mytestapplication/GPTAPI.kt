package com.example.mytestapplication

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class GPTAPI {
    private val client = OkHttpClient()

    fun promptGPT(prompt: String, callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val requestBody = JsonObject().apply {
                addProperty("prompt", prompt)
                addProperty("model", "gemini-1.5-flash") // Replace with the correct model name if needed
            }.toString().toRequestBody("application/json".toMediaTypeOrNull())

            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1alpha2/models/gemini-1.5-flash:generate") // Check the correct endpoint
                .addHeader("Authorization", "Bearer AIzaSyCKzmv67jT2lV_8ZzbhfzgOme08tQXX2Ns")
                .post(requestBody)
                .build()

            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonResponse = JsonParser.parseString(responseBody).asJsonObject
                    val generatedText = jsonResponse.get("output")?.asString // Adjust based on actual response structure
                    withContext(Dispatchers.Main) {
                        callback(generatedText)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("API Error", "Error: ${response.code} ${response.message}")
                        callback(null) // Handle error case
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    callback(null) // Handle exception case
                }
            }
        }
    }
}