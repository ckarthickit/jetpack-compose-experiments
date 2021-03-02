package com.kartdroid.jetpackcomposeexp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.kartdroid.jetpackcomposeexp.ui.JetpackComposeExpTheme
import kotlin.reflect.KClass

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherScreen()
        }
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

    private fun launchActivity(classObj: KClass<out Activity>) {
        startActivity(Intent(this, classObj.java))
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
}


