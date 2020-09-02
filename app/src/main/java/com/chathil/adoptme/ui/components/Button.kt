package com.chathil.adoptme.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.preferredSizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonConstants
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.chathil.adoptme.ui.theme.AdoptmeTheme
import com.example.jetsnack.ui.utils.statusBarsPadding

@Composable
fun AdoptmeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonShape,
    border: BorderStroke? = null,
    color: Color = AdoptmeTheme.colors.btnPrimary,
    contentColor: Color = AdoptmeTheme.colors.btnContent,
    disabledContentColor: Color = AdoptmeTheme.colors.btnContentInactive,
    padding: InnerPadding = ButtonConstants.DefaultContentPadding,
    text: @Composable () -> Unit
) {
    Providers(ContentColorAmbient provides if (enabled) contentColor else disabledContentColor) {
        ProvideTextStyle(MaterialTheme.typography.button) {
            Box(
                modifier = modifier
                    .preferredSizeIn(minWidth = 64.dp, minHeight = 36.dp)
                    .clip(shape)
                    .then(if (border != null) Modifier.border(border, shape) else Modifier)
                    .semantics(mergeAllDescendants = true, properties = { })
                    .clickable(onClick = onClick, enabled = enabled),
                paddingStart = padding.start,
                paddingTop = padding.top,
                paddingEnd = padding.end,
                paddingBottom = padding.bottom,
                gravity = ContentGravity.Center,
                children = text,
                backgroundColor = color
            )
        }
    }
}

@Composable
fun UpButton(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .preferredSize(36.dp)
            .background(
                color = AdoptmeTheme.colors.btnPrimary,
                shape = CircleShape
            )
    ) {
        Icon(
            asset = Icons.Outlined.ArrowBack,
            tint = AdoptmeTheme.colors.btnContent
        )
    }
}

private val ButtonShape = RoundedCornerShape(percent = 50)