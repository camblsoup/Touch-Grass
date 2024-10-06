package com.example.mytestapplication


import com.google.gson.Gson
import okhttp3.MediaType.Companion.get
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import java.io.IOException

class GemmaAPI {

    private val baseUrl = "https://api-inference.huggingface.co/models/google/gemma-2-2b-jpn-it"
    private val client = OkHttpClient()
    private val gson = Gson()

    fun generateText(prompt: String, completionLength: Int = 100): String {
        val jsonBody = JSONObject()
        jsonBody.put("inputs", prompt)
        jsonBody.put("max_length", completionLength)

        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), jsonBody.toString())

        val request = Request.Builder()
            .url(baseUrl)
            .post(requestBody)
            .build()

        try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: return ""
            val responseObject = gson.fromJson(responseBody, ResponseData::class.java)
            return responseObject.generated_text ?: ""
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }

    data class ResponseData(val generated_text: String?)
}