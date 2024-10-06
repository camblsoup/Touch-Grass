package com.example.mytestapplication.models

class User(
    private var firstName : String,
    private var email : String,
    private var password : String
) {
    private var trees : Int = 0

    fun getFirstName() : String {
        return this.firstName
    }

    fun getEmail() : String {
        return this.email
    }

    fun getPassword() : String {
        return this.password
    }

    fun getTrees() : Int {
        return this.trees
    }

    fun incrementTree() {
        this.trees++
    }
}