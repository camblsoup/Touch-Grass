package com.example.mytestapplication.models

import java.time.LocalDate
import java.time.LocalTime

class Activity(
    private var picture: String,
    private var activityTitle: String,
    private var activityDescription: String,
    private var journalLog: String,
    private var dateOfActivity: LocalDate,
    private var startTime: LocalTime
) {
    private var endTime : LocalTime? = null

    fun setEndTime(endTime : LocalTime) {
        this.endTime = endTime
    }

    fun getPicture(): String {
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