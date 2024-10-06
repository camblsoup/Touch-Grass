package com.example.mytestapplication.models

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.example.mytestapplication.R
import com.google.gson.Gson
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

class Activity(
    val context : Context,
    val resources: Resources
) {
    private var activityTitle: String? = null
    private var activityDescription: String? = null
    private var journalLog: String? = null
    private var dateOfActivity: LocalDate? = null
    private var startTime: LocalTime? = null
    private var endTime : LocalTime? = null
    private var picture: String? = null

    private val gpt = GPT()

    fun generateActivity() {
        val rand = (0..5).random()

        // Set the callback to handle the API response
        gpt.setResponseCallback { response ->
            Log.d("SOMETHING", response)  // Log the response string

            // Parse the JSON response using Gson
            val gson = Gson()
            val jsonResponse = gson.fromJson(response, Map::class.java)

            // Extract and log the "activityName" from the parsed JSON map
            jsonResponse?.let {
                Log.d("SOMETHING", it["activityName"].toString())
            } ?: Log.d("SOMETHING", "No activityName found")
        }

        // Call the API using the GPT object
        gpt.callAPI(context.getString(R.string.default_prompt) + resources.getStringArray(R.array.themes)[rand])
    }



    fun setEndTime(endTime : LocalTime) {
        this.endTime = endTime
    }

    fun getPicture(): String? {
        return this.picture
    }

    fun getActivityTitle(): String? {
        return this.activityTitle
    }

    fun getActivityDescription(): String? {
        return this.activityDescription
    }

    fun getJournalLog(): String? {
        return this.journalLog
    }

    fun getDateOfActivity(): LocalDate? {
        return this.dateOfActivity
    }

    fun getStartTime(): LocalTime? {
        return this.startTime
    }

    fun getEndTime(): LocalTime? {
        return this.endTime
    }
}