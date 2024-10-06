package com.example.mytestapplication.models

class User(
    private var firstName : String,
    private var email : String,
    private var password : String
) {
    private var activitiesDone : ArrayList<Activity> = ArrayList()
    private var nextActivity : Activity? = null;

    fun getFirstName() : String {
        return this.firstName
    }

    fun getEmail() : String {
        return this.email
    }

    fun getPassword() : String {
        return this.password
    }

    fun getActivitesDone() : ArrayList<Activity> {
        return this.activitiesDone
    }

    fun addActivity(activity : Activity) {
        this.activitiesDone.add(activity)
    }
}