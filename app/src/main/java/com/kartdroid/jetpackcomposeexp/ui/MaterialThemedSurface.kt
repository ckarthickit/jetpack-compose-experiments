package com.kartdroid.jetpackcomposeexp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
fun MaterialThemedSurface(content: @Composable() () -> Unit) {
    JetpackComposeExpTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}