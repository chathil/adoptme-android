package com.chathil.adoptme.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chathil.adoptme.ui.theme.AdoptmeTheme

@Composable
fun AdoptmeProgressBar(
    progress: Float = 0f,
    modifier: Modifier = Modifier,
    color: Color = AdoptmeTheme.colors.primaryVariant,
    borderColor: Color = AdoptmeTheme.colors.primary
) {
    val progressBarShape = RoundedCornerShape(percent = 50)
    Column(modifier = modifier) {
        Stack {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(16.dp)
                    .border(2.dp, borderColor, progressBarShape)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .preferredHeight(16.dp)
                    .clip(progressBarShape)
                    .background(
                        color, progressBarShape.copy(
                            topRight = CornerSize(0.dp),
                            bottomRight = CornerSize(0.dp)
                        )
                    )
            )
        }
    }
}