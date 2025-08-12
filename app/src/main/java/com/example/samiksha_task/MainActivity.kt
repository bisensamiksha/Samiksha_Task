package com.example.samiksha_task

import HoldingsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.samiksha_task.ui.theme.Samiksha_TaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Samiksha_TaskTheme {
                HoldingsScreen()
            }
        }
    }
}


