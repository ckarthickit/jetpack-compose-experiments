package com.kartdroid.composecodelabs.animate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kartdroid.composecodelabs.theme.ComposeCodeLabsTheme

class AnimateCodeLab: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodeLabsTheme {
                AnimateHomeScreen()
            }
        }
    }
}