package com.kartdroid.jetpackcomposeexp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.kartdroid.jetpackcomposeexp.ui.JetpackComposeExpTheme
import kotlin.reflect.KClass

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherScreen()
        }
    }

    @Composable
    fun FullWidthListItem(text: String, onClick: () -> Unit = { }) {
        TextButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(width = 2.dp, color = MaterialTheme.colors.primary)//BorderStroke(width = 1.dp, brush = SolidColor(Color.Black))
        ) {
            Text(text = text, color = MaterialTheme.colors.primary, modifier = Modifier.padding(8.dp))
        }
    }

    private fun launchActivity(classObj: KClass<out Activity>) {
        startActivity(Intent(this, classObj.java))
    }

    @Preview(showBackground = true)
    @Composable
    fun LauncherScreen() {
        JetpackComposeExpTheme {
            Column {
                FullWidthListItem(text = "SimpleText") { launchActivity(SimpleTextActivity::class) }
            }
        }
    }
}


