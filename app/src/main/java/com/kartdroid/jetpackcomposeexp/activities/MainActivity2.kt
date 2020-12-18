package com.kartdroid.jetpackcomposeexp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
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
    val image = imageResource(R.drawable.header)
    Column(modifier = Modifier.padding(8.dp)) {
        val imageModifier = Modifier
            .preferredHeight(240.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
        Image(
            asset = image,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.preferredHeight(16.dp))
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
    val dynamicImage = loadImageResource(id = resId)
    dynamicImage.resource.resource?.let {
        Column(modifier = Modifier.padding(8.dp)) {
            val imageModifier = Modifier
                .preferredHeight(240.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
            Image(
                asset = it,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.preferredHeight(16.dp))
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