package com.kartdroid.composecodelabs

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kartdroid.composecodelabs.ScreenType.ANIMATING_LIST
import com.kartdroid.composecodelabs.ScreenType.COMPOSE_AND_STATES
import com.kartdroid.composecodelabs.ScreenType.FLEXIBLE_LAYOUTS
import com.kartdroid.composecodelabs.ScreenType.LAZY_COLUMN
import com.kartdroid.composecodelabs.theme.ComposeCodeLabsTheme
import com.kartdroid.composecodelabs.theme.Orange

enum class ScreenType {
    COMPOSE_AND_STATES,
    FLEXIBLE_LAYOUTS,
    LAZY_COLUMN,
    ANIMATING_LIST
}
class BasicCodeLab : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicPage()
        }
    }
}

@Composable
fun BasicPage() {
    BasicSurface {
        val screenType: MutableState<ScreenType> = remember { mutableStateOf(ScreenType.COMPOSE_AND_STATES) }
        Column {
            Row {
                Button(onClick = { screenType.value = COMPOSE_AND_STATES }) {
                    Text(text = "Compose+States")
                }
                Button(onClick = { screenType.value = FLEXIBLE_LAYOUTS }) {
                    Text(text = "FlexiLayout")
                }
            }
            Row {
                Button(onClick = { screenType.value = LAZY_COLUMN }) {
                    Text(text = "LazyColumn")
                }
                Button(onClick = { screenType.value = ANIMATING_LIST }) {
                    Text(text = "Animating List")
                }
            }
            Divider(color = Color.Black)

            when(screenType.value) {
                COMPOSE_AND_STATES -> {
                    ComposeAndStatesDemo()
                }
                FLEXIBLE_LAYOUTS -> {
                    FlexiLayoutDemo()
                }
                LAZY_COLUMN -> {
                    LazyColumnDemo()
                }
                ANIMATING_LIST -> {
                    AnimatedList()
                }
            }
        }
    }
}


/*********************************/

@Composable
fun BasicSurface(content: @Composable () -> Unit) {
    ComposeCodeLabsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun ComposeAndStatesDemo() {
    val (counter, setCounter) = remember { mutableStateOf(0) }
    Column {
        Greeting("Android")
        val names = listOf("Daisy", "Lilly")

        for (name in names) {
            Text(text = name)
            Divider(color = Color.Black)
        }

        StateDemoCounter()
        StateDemoCounter2()
        StateHoistedCounter(counter = counter, setCounter = setCounter)
    }
}

@Composable
fun StateDemoCounter() {
    val (counter, setCounter) = remember { mutableStateOf(0) }
    Button(onClick = { setCounter(counter + 1) }) {
        Text("$counter")
    }
}

@Composable
fun StateDemoCounter2() {
    var counter by remember { mutableStateOf(0) }
    Button(onClick = { counter++; }) {
        Text("$counter")
    }
}

@Composable
fun StateHoistedCounter(
    counter: Int,
    setCounter: (Int)-> Unit
) {
    Button(onClick = { setCounter(counter + 1) }) {
        Text("$counter")
    }
}


/**************************** FLEXI LAYOUT DEMO ********************/

@Composable
fun FlexiLayoutDemo() {
    Column(modifier = Modifier.fillMaxHeight()) {
        val (counter,updateCounter) = remember { mutableStateOf(0) }
        val names = listOf("android", "there")
        FlexiColumn {
            for(name in names) {
                Text(text = name)
                Divider(color =  Color.Black)
            }
        }
        StateHoistedCounter(counter, updateCounter)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicPage()
}

@Composable
fun ColumnScope.FlexiColumn(content: @Composable () -> Unit) {
    Column(modifier = Modifier.weight(1.0f)) {
        content()
    }
}


/****************************/

@Composable
fun LazyColumnDemo() {
    val items = List(100) { "Item no #$it"}
    NameList(items = items)
}

@Composable
fun NameList(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        this.items(items = items) { item ->
            Greeting(name = item)
            Divider(color = Color.Black)
        }
    }
}


/********************** ANIMATING LIST ******************/

@Composable
fun AnimatedList() {
    val items = List(100) { "Item no #$it"}
    AnimatedNameList(items = items)
}

@Composable
fun AnimatedNameList(items: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        this.items(items = items) { item ->
            AnimatedGreeting(name = item)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun AnimatedGreeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue = if(isSelected) Orange else Color.Transparent)
    Text(
        text = name,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

/******************* MISC ****************/

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(4.dp))
}