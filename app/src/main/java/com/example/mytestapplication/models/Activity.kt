package com.example.mytestapplication.models

import android.content.Context
import android.content.res.Resources
import com.example.mytestapplication.R
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

    fun generateActivity() {
        val rand = (0..5).random()
        GPT.callAPI(context.getString(R.string.default_prompt) + resources.obtainTypedArray(R.array.themes).getIndex(rand))
    }

    fun setEndTime(endTime : LocalTime) {
        this.endTime = endTime
    }

    fun getPicture(): String? {
        return this.picture
    }

    fun getActivityTitle(): String {
        return this.activityTitle
    }

    fun getActivityDescription(): String {
        return this.activityDescription
    }

    fun getJournalLog(): String {
        return this.journalLog
    }

    fun getDateOfActivity(): LocalDate {
        return this.dateOfActivity
    }

    fun getStartTime(): LocalTime {
        return this.startTime
    }

    fun getEndTime(): LocalTime? {
        return this.endTime
    }
}