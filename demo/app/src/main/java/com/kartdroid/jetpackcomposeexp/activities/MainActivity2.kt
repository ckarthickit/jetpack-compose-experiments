package com.kartdroid.jetpackcomposeexp.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.kartdroid.jetpackcomposeexp.R
import com.kartdroid.jetpackcomposeexp.ui.MaterialThemedSurface

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialThemedSurface {
                Column() {
                    NewStory()
                    AnotherStory(R.drawable.header)
                }
            }
        }
    }
}

@Composable
fun NewStory() {
    val image = painterResource(R.drawable.header)
    Column(modifier = Modifier.padding(8.dp)) {
        val imageModifier = Modifier
            .height(IntrinsicSize.Max)
            .height(240.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
        Image(
            painter = image,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(IntrinsicSize.Max).height(16.dp))
        Text(
            "A day wandering through the sandhills in Shark Fin Cove," +
                    " and a few of the sights I saw",
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            "Davenport, California",
            style = MaterialTheme.typography.body2
        )
        Text(
            "December 2020",
            style = MaterialTheme.typography.body2
        )
    }
}


@Composable
fun AnotherStory(@DrawableRes resId: Int) {
    val dynamicImage = painterResource(id = resId)
        Column(modifier = Modifier.padding(8.dp)) {
            val imageModifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
            Image(
                painter = dynamicImage,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "A day wandering through the sandhills in Shark Fin Cove," +
                        " and a few of the sights I saw",
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = MaterialTheme.typography.body2
            )
            Text(
                "December 2020",
                style = MaterialTheme.typography.body2
            )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultM2Preview() {
    MaterialThemedSurface {
        Column() {
            NewStory()
            AnotherStory(R.drawable.header)
        }
    }
}