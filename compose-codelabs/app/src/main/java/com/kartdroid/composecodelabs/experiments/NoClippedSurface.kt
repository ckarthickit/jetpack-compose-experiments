package com.kartdroid.composecodelabs.experiments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.kartdroid.composecodelabs.R

@Composable
fun NonClippedSurface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = LocalIndication.current,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    clickAndSemanticsModifier: Modifier = Modifier.clickable(
        interactionSource = interactionSource,
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    ),
    content: @Composable () -> Unit
) {
    val elevationOverlay = LocalElevationOverlay.current
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    val backgroundColor = if (color == MaterialTheme.colors.surface && elevationOverlay != null) {
        elevationOverlay.apply(color, absoluteElevation)
    } else {
        color
    }
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier
                .shadow(elevation, shape, clip = false)
                .then(if (border != null) Modifier.border(border, shape) else Modifier)
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .then(clickAndSemanticsModifier),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}


@Composable
fun K3CardView(
    width: Dp = 200.dp,
    height: Dp = 130.dp
) {
    NonClippedSurface(
        onClick = { /*TODO*/ },
        modifier = Modifier
            /* .width(width)
             .height(height),*/
            .wrapContentSize()
    ) {
        val color = remember {
            //Color.Magenta.copy(alpha = 0.2f)
            Color.White
        }
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            modifier = Modifier
                //.shadow(8.dp, RoundedCornerShape(12.dp))
                .width(width)
                .height(height)
                .padding(0.dp)
        ) {

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center,
            modifier = Modifier
                .width(width)
                .height(height)
                .padding(0.dp)
        ) {
            //val (image, text, card) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.icon_subjects_science),
                contentDescription = "",
                modifier = Modifier
                    .offset(0.dp, -30.dp)
                /*.constrainAs(image) {
                    bottom.linkTo(image.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }*/
            )
            Text(
                text = "Something",
                Modifier
                    .padding(2.dp)
                    .offset(0.dp, -30.dp)
                /*.constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(image.bottom)
                    end.linkTo(parent.end)
                }*/
            )
        }
    }
}
@Preview
@Composable
fun PreviewSurface() {
    Surface(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            K3CardView()
            Spacer(modifier = Modifier.size(8.dp))
            K3CardView()
            Spacer(modifier = Modifier.size(8.dp))
            K3CardView()
        }
    }
}