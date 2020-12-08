package com.kartdroid.jetpackcomposeexp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.kartdroid.jetpackcomposeexp.ui.JetpackComposeExpTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemedSurface {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("KC", "Android", "Compose")) {
    Column(modifier = Modifier.fillMaxHeight()) {
        /**
         * Since this Column is flexible and the rest of the content is inflexible,
         * it will take up as much space as it can
         */
        Column(modifier = Modifier.weight(.8f)) {
            val counter = remember { mutableStateOf(0) }
            names.forEach {
                Greeting(it, Modifier.padding(4.dp))
                Divider(color = Color.Black)
            }
            Divider()
            Counter(counter.value) { value -> counter.value = value }
        }
        Column(modifier = Modifier.weight(.2f)) {
            val counter = remember { mutableStateOf(0) }
            Counter(counter.value) { value -> counter.value = value }
        }
        /**
         * The below Composable-s are inflexible
         */
        Divider()
        Text(text = "Another Text")
    }
}


/**
 *  Re-Usable Composable-s
 */

@Composable
fun ThemedSurface(content: @Composable() () -> Unit) {
    JetpackComposeExpTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier) {
    Text(text = "Hello $name!", modifier)
}

@Composable
fun Counter(counter: Int, updateCount: (newValue: Int) -> Unit) {
    Button(
        onClick = { updateCount(counter + 1) },
        contentPadding = PaddingValues(0.dp),
    ) {
        Text("I've clicked $counter times", modifier = Modifier.padding(0.dp))
    }
}

/** PREVIEW **/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemedSurface {
        MyScreenContent()
    }
}