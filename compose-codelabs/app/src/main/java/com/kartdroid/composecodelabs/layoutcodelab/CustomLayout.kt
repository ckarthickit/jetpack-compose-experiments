package com.kartdroid.composecodelabs.layoutcodelab

import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.kartdroid.composecodelabs.themecodelabs.theme.CodeLabTheme

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            //################### MEASURE #################################
            val placeables = measurables.map {
                it.measure(constraints = constraints)
            }
    
            //################### LAYOUT #################################
            layout(constraints.maxWidth, constraints.maxHeight) {
                var yOffset = 0
                placeables.forEach {
                    it.placeRelative(0, yOffset)
                    yOffset = yOffset.plus(it.height)
                }
            }
        }
    )
}


@Preview
@Composable
fun CustomColumnPreview() {
    CodeLabTheme {
        Surface {
            MyColumn {
                Text(text = "First Line")
                Text(text = "Another Text with \n multiple lines")
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Click Here")
                }
            }
        }
    }
}