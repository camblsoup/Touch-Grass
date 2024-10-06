package com.example.mytestapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytestapplication.models.Activity
import com.example.mytestapplication.ui.theme.MyTestApplicationTheme

class MainActivity : ComponentActivity() {
    private fun createNotificationChannel() {
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =
            NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                description = descriptionText
            }
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        createNotificationChannel()
        var activity = Activity(this, this.resources)
        activity.generateActivity()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.frontpage)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTestApplicationTheme {
        Greeting("Android")
    }
}