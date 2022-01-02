## Measure and Draw

### Measure Pass

```shell
	at com.kartdroid.composecodelabs.layoutcodelab.CustomModifierKt$logLayout$1.invoke-3p2s80s(CustomModifier.kt:22)
	at androidx.compose.ui.node.ModifiedLayoutNode.measure-BRTryo0(ModifiedLayoutNode.kt:39)
	at androidx.compose.ui.node.DelegatingLayoutNodeWrapper.measure-BRTryo0(DelegatingLayoutNodeWrapper.kt:116)
	at com.kartdroid.composecodelabs.layoutcodelab.CustomModifierKt$paddingBaseLine$1.invoke-3p2s80s(CustomModifier.kt:47)
	at androidx.compose.ui.layout.LayoutModifierImpl.measure-3p2s80s(LayoutModifier.kt:283)
	at androidx.compose.ui.node.ModifiedLayoutNode.measure-BRTryo0(ModifiedLayoutNode.kt:39)
	at androidx.compose.ui.node.OuterMeasurablePlaceable$remeasure$3.invoke(OuterMeasurablePlaceable.kt:100)
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.foundation.layout.BoxKt$boxMeasurePolicy$1.measure-3p2s80s(Box.kt:115)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1.measure-3p2s80s(RowColumnImpl.kt:89)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.ui.layout.RootMeasurePolicy.measure-3p2s80s(RootMeasurePolicy.kt:38)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
	at androidx.compose.ui.node.OuterMeasurablePlaceable.remeasure-BRTryo0(OuterMeasurablePlaceable.kt:99)
	at androidx.compose.ui.node.LayoutNode.remeasure-_Sx5XlM$ui_release(LayoutNode.kt:1236)
	at androidx.compose.ui.node.MeasureAndLayoutDelegate.doRemeasure-0kLqBqw(MeasureAndLayoutDelegate.kt:169)
	at androidx.compose.ui.node.MeasureAndLayoutDelegate.measureAndLayout(MeasureAndLayoutDelegate.kt:207)
	at androidx.compose.ui.platform.AndroidComposeView.onMeasure(AndroidComposeView.android.kt:547)
	at android.view.View.measure(View.java:27131)
	at androidx.compose.ui.platform.AbstractComposeView.internalOnMeasure$ui_release(ComposeView.android.kt:278)
	at androidx.compose.ui.platform.AbstractComposeView.onMeasure(ComposeView.android.kt:265)
```

If we see the above stacktrace:

```shell
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.foundation.layout.BoxKt$boxMeasurePolicy$1.measure-3p2s80s(Box.kt:115)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1.measure-3p2s80s(RowColumnImpl.kt:89)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
	at androidx.compose.ui.node.LayoutNode.measure-BRTryo0(LayoutNode.kt:1227)
	at androidx.compose.ui.layout.RootMeasurePolicy.measure-3p2s80s(RootMeasurePolicy.kt:38)
	at androidx.compose.ui.node.InnerPlaceable.measure-BRTryo0(InnerPlaceable.kt:43)
```

`Placeable#measue => MeasurePolicy#measure =>  LayoutNode#measure` pattern repeats recursively.

--- 

### Draw Pass

```shell
	at com.kartdroid.composecodelabs.layoutcodelab.CustomDrawModifier.draw(CustomModifier.kt:36)
	at androidx.compose.ui.node.ModifiedDrawNode.performDraw(ModifiedDrawNode.kt:102)
	at androidx.compose.ui.draw.DrawBackgroundModifier.draw(DrawModifier.kt:102)
	at androidx.compose.ui.node.ModifiedDrawNode.performDraw(ModifiedDrawNode.kt:102)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.ModifiedLayoutNode.performDraw(ModifiedLayoutNode.kt:98)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
	at androidx.compose.ui.platform.RenderNodeLayer.drawLayer(RenderNodeLayer.android.kt:224)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:239)
	at androidx.compose.ui.node.ModifiedDrawNode.performDraw(ModifiedDrawNode.kt:102)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
	at androidx.compose.ui.platform.RenderNodeLayer.drawLayer(RenderNodeLayer.android.kt:224)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:239)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.DelegatingLayoutNodeWrapper.performDraw(DelegatingLayoutNodeWrapper.kt:68)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.DelegatingLayoutNodeWrapper.performDraw(DelegatingLayoutNodeWrapper.kt:68)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.DelegatingLayoutNodeWrapper.performDraw(DelegatingLayoutNodeWrapper.kt:68)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.platform.AndroidComposeView.dispatchDraw(AndroidComposeView.android.kt:672)
	at android.view.View.draw(View.java:23904)
```

If we see the above stacktrace:

```shell
	at androidx.compose.ui.node.ModifiedLayoutNode.performDraw(ModifiedLayoutNode.kt:98)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
	at androidx.compose.ui.platform.RenderNodeLayer.drawLayer(RenderNodeLayer.android.kt:224)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:239)
	at androidx.compose.ui.node.ModifiedDrawNode.performDraw(ModifiedDrawNode.kt:102)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:244)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
	at androidx.compose.ui.platform.RenderNodeLayer.drawLayer(RenderNodeLayer.android.kt:224)
	at androidx.compose.ui.node.LayoutNodeWrapper.draw(LayoutNodeWrapper.kt:239)
	at androidx.compose.ui.node.LayoutNode.draw$ui_release(LayoutNode.kt:814)
	at androidx.compose.ui.node.InnerPlaceable.performDraw(InnerPlaceable.kt:105)
```

`Placeable#performDraw => LayoutNode#draw => LayoutNodeWrapper#draw => (ModifiedDrawNode#performDraw => LayoutNodeWrapper#draw) => RenderNodeLayer#drawLayer`
pattern repeats recursively.

### Custom Modifiers

```kotlin
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
        
        //Second Measure needs to offset the `vertical space` occupied  in this modifier in the Constraints
        // so that subsequent modifiers only have a "reduced constraint"
        placeable = measurable.measure(constraints.offset(0, -vertical))
        println("::KC_DEBUG:($paddingToBaseline) placeable size= (${placeable.width},${placeable.height}) baseline: ${placeable[FirstBaseline]}")
        println("::KC_DEBUG:($paddingToBaseline) placeable measured_size= (${placeable.width},${placeable.height})")
        
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
```

> Used to modify measurement and layout of a  single `LayouNode` (or) Composable

### Custom Layout

```kotlin
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
```

> Used to measure and Layout all child `LayoutNode`s and `Composable`s
