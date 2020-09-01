package com.chathil.adoptme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource

@Composable
fun CircleImage(modifier: Modifier = Modifier, asset: ImageAsset) {
    AdoptmeSurface(
        color = Color.LightGray,
        shape = CircleShape,
        modifier = modifier
    ) {
        Image(
            asset = asset,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
