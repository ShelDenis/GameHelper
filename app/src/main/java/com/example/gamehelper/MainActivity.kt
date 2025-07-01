package com.example.gamehelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gamehelper.ui.theme.GameHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var nd = NecessaryData()
        var md = MunchkinData()
        setContent {
            GameHelperTheme() {
                Navigation(nd, md)
            }
        }

    }
}