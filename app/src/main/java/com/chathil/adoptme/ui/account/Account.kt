package com.chathil.adoptme.ui.account

import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.chathil.adoptme.model.User
import com.chathil.adoptme.model.fake
import com.chathil.adoptme.ui.components.AdoptmeScaffold
import com.chathil.adoptme.ui.components.CircleImage
import com.chathil.adoptme.ui.components.UpButton
import com.chathil.adoptme.ui.theme.AdoptmeTheme
import com.chathil.adoptme.ui.theme.AlphaNearTransparent
import com.chathil.adoptme.ui.theme.padding
import com.example.jetsnack.ui.utils.SysUiController
import com.chathil.adoptme.R
import com.chathil.adoptme.ui.components.AdoptmeSurface

@Composable
fun Account(upPress: () -> Unit) {
    SysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    val user = User.fake
    AdoptmeScaffold { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        ScrollableColumn {
            Header(user, upPress)
            Body(modifier, user)
        }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier, user: User) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = padding)) {
        Text(
            user.fullName,
            style = MaterialTheme.typography.h4,
            color = AdoptmeTheme.colors.textPrimary
        )
        Text(
            user.email,
            style = MaterialTheme.typography.h6,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Credits",
            style = MaterialTheme.typography.h4,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Unsplash\nfor the awsome pet photos",
            style = MaterialTheme.typography.body1,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Freepik\nfor the illustrations @pikisuperstar",
            style = MaterialTheme.typography.body1,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "FontAwesome\nfor the pet icons",
            style = MaterialTheme.typography.body1,
            color = AdoptmeTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            asset = imageResource(id = R.drawable.ill_dog),
            modifier = Modifier.preferredSize(216.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun Header(
    user: User,
    upPress: () -> Unit
) {
    Stack(modifier = Modifier.wrapContentHeight().padding(bottom = 46.dp)) {
        Box(
            backgroundColor = AdoptmeTheme.colors.btnActive,
            shape = CircleShape,
            modifier = Modifier.size(112.dp).offset(y = 90.dp)
        )
        CircleImage(
            asset = imageResource(id = user.photo),
            modifier = Modifier.fillMaxWidth().height(316.dp).offset(x = 96.dp)
        )
        Box(
            backgroundColor = AdoptmeTheme.colors.btnActive,
            shape = CircleShape,
            modifier = Modifier.size(156.dp).offset(x = 36.dp, y = 196.dp)
        )
        UpButton(upPress)
    }
}

@Preview
@Composable
fun BodyLightPreview() {
    AdoptmeTheme {
        AdoptmeSurface {
            Body(user = User.fake)
        }
    }
}

@Preview
@Composable
fun BodyDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            Body(user = User.fake)
        }
    }
}

@Preview
@Composable
fun HeaderPreview() {
    AdoptmeTheme {
        Header(user = User.fake, upPress = {})
    }
}

@Preview
@Composable
fun AccountPreview() {
    Account {}
}