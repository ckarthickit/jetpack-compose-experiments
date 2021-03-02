package com.kartdroid.jetpackcomposeexp.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class StyledTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StyledTextScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun StyledTextScreen() {
        LazyColumn {
            item {
                CustomStyledText(displayText = "Default Text Style -> (no style)")
                CustomStyledText(
                    displayText = "Text in Blue Color -> color",
                    style = TextStyle(color = Color.Blue)
                )
                CustomStyledText(
                    displayText = "Text with Bigger Font -> Font Size",
                    style = TextStyle(fontSize = 24.sp)
                )
                CustomStyledText(
                    displayText = "Bold Text -> FontWeight",
                    style = TextStyle(fontWeight = FontWeight.W700)
                )
                CustomStyledText(
                    displayText = "Italic Text -> Font Style",
                    style = TextStyle(fontStyle = FontStyle.Italic)
                )
                CustomStyledText(
                    displayText = "Cursive Writing -> Font Family",
                    style = TextStyle(fontFamily = FontFamily.Cursive)
                )
                CustomStyledText(
                    displayText = "Underlined Text -> TextDecoration.Underline ",
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
                CustomStyledText(
                    displayText = "StrikeThrough Text -> TextDecoration.LineThrough ",
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                CustomStyledText(
                    displayText = "Trying to fit in a longer text" +
                            "in one line and hence it will be ellipsized as per overflow behaviour",
                    maxLines = 1
                )
                CustomStyledText(
                    displayText = "Shadowed Text -> TextStyle(shadow)",
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            blurRadius = 6f,
                            offset = Offset(4f, 4f)
                        )
                    )
                )

                Divider(color = Color.Gray)

                CustomStyledText(
                    "This text will demonstrate how to \"center\" " +
                    "align the text of your paragraph that includes soft line breaks",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )

                CustomStyledText(
                    "This text will demonstrate how to \"justify\" " +
                            "your paragraph to ensure that the text that ends with a soft " +
                            "line break spreads and takes the entire width of the container",
                    style = TextStyle(
                        textAlign = TextAlign.Justify
                    )
                )

                CustomStyledText(
                    "This text will demonstrate how to add " +
                            "\"indentation\" to your text. In this example, indentation was only " +
                            "added to the first line. You also have the option to add " +
                            "indentation to the rest of the lines if you'd like",
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        textIndent = TextIndent(firstLine = 30.sp)
                    )
                )

                CustomStyledText(
                    "The line height of this text has been " +
                            "increased hence you will be able to see more space between each " +
                            "line in this paragraph.",
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        lineHeight = 20.sp
                    )
                )

                Divider(color = Color.Magenta)

                val annotatedString = with(AnnotatedString.Builder()) {
                    append("This string has style spans")
                    addStyle(style = SpanStyle(color = Color.Red), start = 0, end = 4)
                    addStyle(style = SpanStyle(color = Color.Green), start = 5, end = 21)
                    addStyle(style = SpanStyle(color = Color.Blue), start = 22, end = 27)
                    toAnnotatedString()
                }

                Text(annotatedString, modifier = Modifier.padding(16.dp))

                Divider(color = Color.Green, thickness = 4.dp)

                // Surface is a composable provided to fulfill the needs of the "Surface"
                // metaphor from the Material Design specification. It's generally used to
                // change the background color, add elevation, clip or add background shape
                // to its children composables.
                Surface(
                    color = Color.Yellow,
                    elevation = 4.dp,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "This text has a background color",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }


    // Widgets

    @Composable
    fun CustomStyledText(
        displayText: String,
        style: TextStyle? = null,
        maxLines: Int = Int.MAX_VALUE
    ) {
        Text(
            text = displayText,
            modifier = Modifier.padding(8.dp),
            style = style ?: TextStyle.Default,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}