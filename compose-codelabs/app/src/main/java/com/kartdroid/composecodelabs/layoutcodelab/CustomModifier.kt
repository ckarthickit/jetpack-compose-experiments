package com.kartdroid.composecodelabs.layoutcodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.kartdroid.composecodelabs.theme.ComposeCodeLabsTheme

fun Modifier.logLayout() = this.layout { measurable, constraints ->
    Exception("::KC_DEBUG:: measure_pass1").printStackTrace()
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        Exception("::KC_DEBUG:: measure_pass2").printStackTrace()
        placeable.placeRelative(1, 1)
    }
}

fun Modifier.logDraw() = this.drawBehind {
    Exception("::KC_DEBUG:: draw_pass1").printStackTrace()
}

class CustomDrawModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        Exception("::KC_DEBUG:: custom_draw").printStackTrace()
        drawContent()
    }
    
}


@Stable
fun Modifier.myPadding(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
) = this.then(
    PaddingModifier(
        start = start,
        top = top,
        end = end,
        bottom = bottom,
        rtlAware = true,
        inspectorInfo = debugInspectorInfo {
            name = "padding"
            properties["start"] = start
            properties["top"] = top
            properties["end"] = end
            properties["bottom"] = bottom
        }
    )
)

private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean,
    inspectorInfo: InspectorInfo.() -> Unit
) : LayoutModifier, InspectorValueInfo(inspectorInfo) {
    init {
        require(
            (start.value >= 0f || start == Dp.Unspecified) &&
                (top.value >= 0f || top == Dp.Unspecified) &&
                (end.value >= 0f || end == Dp.Unspecified) &&
                (bottom.value >= 0f || bottom == Dp.Unspecified)
        ) {
            "Padding must be non-negative"
        }
    }
    
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        
        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()
        println("::KC_DEBUG_PADDING:($start,$end) horizontal= (${start.roundToPx()},${end.roundToPx()})")
        println("::KC_DEBUG_PADDING:($start,$end) vertical= (${top.roundToPx()},${bottom.roundToPx()})")
        println("::KC_DEBUG_PADDING:($start,$end) constraints= (${constraints}")
        println(
            "::KC_DEBUG_PADDING:($start,$end) constraints with offset = (${
                constraints.offset(
                    -horizontal,
                    -vertical
                )
            }"
        )
        
        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))
        println("::KC_DEBUG_PADDING:($start,$end) placeable size= (${placeable.width},${placeable.height}) baseline: ${placeable[FirstBaseline]}")
        println("::KC_DEBUG_PADDING:($start,$end) placeable measured_size= (${placeable.width},${placeable.height})")
        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        println("::KC_DEBUG_PADDING:($start,$end) placeable constrained_size= (${width},${height})")
        return layout(width, height) {
            if (rtlAware) {
                placeable.placeRelative(start.roundToPx(), top.roundToPx())
            } else {
                placeable.place(start.roundToPx(), top.roundToPx())
            }
        }
    }
    
    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + top.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + bottom.hashCode()
        result = 31 * result + rtlAware.hashCode()
        return result
    }
    
    override fun equals(other: Any?): Boolean {
        val otherModifier = other as? PaddingModifier ?: return false
        return start == otherModifier.start &&
            top == otherModifier.top &&
            end == otherModifier.end &&
            bottom == otherModifier.bottom &&
            rtlAware == otherModifier.rtlAware
    }
}


fun Modifier.paddingToFirstBaseLine(
    paddingToBaseline: Dp
) = this.then(
    layout { measurable, constraints ->
        
        println("::KC_DEBUG:($paddingToBaseline) constraints= (${constraints}")
        //First Measure is needed to get the `BaseLine` offset without considering `paddingToBaseline`
        var placeable = measurable.measure(constraints.offset(0, 0))
        var firstBaseLineOffset = placeable[FirstBaseline]
        check(firstBaseLineOffset != AlignmentLine.Unspecified)
        println("::KC_DEBUG:($paddingToBaseline), firstBaseLine: $firstBaseLineOffset")
        //The "extra vertical space" that this modifier is gonna occupy
        //if paddingToBaseLine is less than firstBaseLineOffset from top, then don't consider it
        val vertical = (paddingToBaseline.roundToPx() - firstBaseLineOffset).coerceAtLeast(0)
        
        //################### MEASURE #################################
        //Second Measure needs to offset the `vertical space` occupied  in this modifier in the Constraints
        // so that subsequent modifiers only have a "reduced constraint"
        placeable = measurable.measure(constraints.offset(0, -vertical))
        println("::KC_DEBUG:($paddingToBaseline) placeable size= (${placeable.width},${placeable.height}) baseline: ${placeable[FirstBaseline]}")
        println("::KC_DEBUG:($paddingToBaseline) placeable measured_size= (${placeable.width},${placeable.height})")
        
        //################### LAYOUT #################################
        //during layout, add  `vertical space` occupied by this  modifier into the height
        // relative to the `y` position that will be offset when placing
        val placeableHeight = constraints.constrainHeight(placeable.height + vertical)
        println("::KC_DEBUG:($paddingToBaseline) placeable constrained_size= (${placeable.width},${placeableHeight})")
        layout(placeable.width, placeableHeight) {
            //during layout, offset the `y` position of the placeable by `vertical space`.
            //Remember that we have already adjusted the height accordingly
            placeable.placeRelative(0, vertical)
        }
    }
)


@Preview
@Composable
fun CustomPaddingModifierPreview() {
    ComposeCodeLabsTheme {
        Surface(
            color = Color.Yellow.copy(alpha = 0.75f)
        ) {
            val otherTopPaddings = remember {
                8.dp
            }
            Column {
                Text(
                    modifier = Modifier
                        .paddingToFirstBaseLine(18.dp + otherTopPaddings)
                        .drawBehind {
                            drawRect(
                                brush = Brush.sweepGradient(
                                    colors = listOf(
                                        Color.Gray,
                                        Color.Gray.copy(alpha = 0.7f)
                                    ),
                                    center = Offset(
                                        this.size.width / 2,
                                        this.size.height / 2
                                    )
                                )
                            )
                        }
                        .myPadding(start = 4.dp, end = 8.dp, top = otherTopPaddings, bottom = 8.dp)
                        .drawBehind {
                            drawRect(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red,
                                        Color.White,
                                        Color.Green
                                    )
                                )
                            )
                        }
                        .then(CustomDrawModifier())
                        .logLayout()
                        .logDraw()
                        .also {
                            println("::KC_DEBUG:: ====modifiers==== $it")
                        },
                    text = "Sample"
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPaddingModifierPreview() {
    ComposeCodeLabsTheme {
        Surface(
            color = Color.Yellow.copy(alpha = 0.75f)
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .myPadding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .drawBehind {
                            drawRect(
                                brush = Brush.sweepGradient(
                                    colors = listOf(
                                        Color.Gray,
                                        Color.Gray.copy(alpha = 0.7f)
                                    ),
                                    center = Offset(
                                        this.size.width / 2,
                                        this.size.height / 2
                                    )
                                )
                            )
                        }
                        .myPadding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 18.dp)
                        .drawBehind {
                            drawRect(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red,
                                        Color.White,
                                        Color.Green
                                    )
                                )
                            )
                        }
                        .then(CustomDrawModifier())
                        .logLayout()
                        .logDraw()
                        .also {
                            println("::KC_DEBUG:: ====modifiers==== $it")
                        },
                    text = "Sample"
                )
            }
        }
    }
}