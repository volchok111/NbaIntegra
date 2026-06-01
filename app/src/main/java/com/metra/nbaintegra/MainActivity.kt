package com.metra.nbaintegra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.metra.nbaintegra.core.theme.NbaIntegraTheme
import com.metra.nbaintegra.navigation.NbaNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NbaIntegraTheme {
                NbaNavHost()
            }
        }
    }
}
