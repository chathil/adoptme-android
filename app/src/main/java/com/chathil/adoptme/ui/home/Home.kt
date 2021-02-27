package com.chathil.adoptme.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chathil.adoptme.R
import com.chathil.adoptme.model.Pet
import com.chathil.adoptme.model.fake
import com.chathil.adoptme.model.icon
import com.chathil.adoptme.model.image
import com.chathil.adoptme.ui.AdoptmeAppState
import com.chathil.adoptme.ui.Screen
import com.chathil.adoptme.ui.components.*
import com.chathil.adoptme.ui.theme.AdoptmeTheme
import com.chathil.adoptme.ui.theme.AlphaNearTransparent
import com.chathil.adoptme.ui.theme.padding
import com.chathil.adoptme.ui.utils.LocalSysUiController
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import java.util.*

@Composable
fun Home(
    navigateTo: (Screen) -> Unit, appState: AdoptmeAppState
) {
    LocalSysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    AdoptmeScaffold { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(horizontal = padding)
        ) {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .height(16.dp)
            )
            AccountSection(name = "Chathil", modifier) { navigateTo(Screen.Account) }
            FindSection(modifier = modifier)
            Spacer(modifier = Modifier.height(16.dp))
            appState.pets.forEach {
                PetCard(pet = it,
                    onLikeClick = { pet -> appState.like(pet) },
                    onPetClick = { pet ->  navigateTo(Screen.Detail(appState.pets.indexOf(pet)))}
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
private fun AccountSection(
    name: String,
    modifier: Modifier = Modifier,
    onAccountClicked: () -> Unit
) {
    Column {
        Row(modifier = modifier.wrapContentHeight(Alignment.CenterVertically)) {
            Column(Modifier.weight(1F)) {
                Text(
                    name,
                    style = MaterialTheme.typography.h4,
                    color = AdoptmeTheme.colors.textPrimary
                )
                Text(
                    "Lorem ipsum dolor sit amet,consectetur adipiscing elit, sed do",
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.height(8.dp))
                AdoptmeButton(onClick = onAccountClicked) {
                    Row {
                        ProvideTextStyle(value = MaterialTheme.typography.body1) {
                            Text("Account")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.ChevronRight,
                            tint = AdoptmeTheme.colors.btnContent,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
            CircleImage(modifier = Modifier.size(116.dp), painterResource(id = R.drawable.me))
        }
        AccountCompletion(modifier = modifier)
    }
}

@Composable
private fun AccountCompletion(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("Complete Your Profile", modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
        AdoptmeProgressBar(progress = 0.7f, modifier = Modifier.width(216.dp))
    }
}

@Composable
private fun FindSection(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        AdoptmeSurface(
            color = Color.Transparent,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ill_cat), modifier = Modifier.width(116.dp), contentDescription = null)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight()) {
            Text(
                "Find", style = MaterialTheme.typography.h4, color = AdoptmeTheme.colors.textPrimary
            )
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
private fun PetCard(
    pet: Pet,
    modifier: Modifier = Modifier,
    onPetClick: (Pet) -> Unit,
    onLikeClick: (Pet) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(116.dp)
            .clickable(onClick = { onPetClick(pet) })
    ) {
        AdoptmeSurface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.size(116.dp),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Image(
                painter = painterResource(id = pet.image(LocalContext.current)),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    pet.name,
                    style = MaterialTheme.typography.h6,
                    color = AdoptmeTheme.colors.textPrimary
                )
                Text(pet.location, style = MaterialTheme.typography.body2)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    start = {
                        Icon(
                            painter = painterResource(
                                id = pet.icon()
                            ),
                            tint = AdoptmeTheme.colors.primary,
                            modifier = Modifier.size(16.dp),
                            contentDescription = null
                        )
                    },
                    content = {
                        Text(
                            pet.type.name.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT),
                            color = AdoptmeTheme.colors.primary
                        )
                    }
                )
                IconButton(
                    onClick = { onLikeClick(pet) }
                ) {
                    Icon(
                        imageVector = if (pet.isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        tint = AdoptmeTheme.colors.btnLike,
                        contentDescription = null
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Divider(
        modifier = Modifier.padding(start = 124.dp),
        color = AdoptmeTheme.colors.primaryVariant.copy(
            AlphaNearTransparent
        )
    )
}

@Preview
@Composable
fun AccountSectionLightPreview() {
    AdoptmeTheme {
        AccountSection(name = "Chathil") {}
    }
}

@Preview
@Composable
fun AccountSectionDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            AccountSection(name = "Chathil") {}
        }
    }
}

@Preview
@Composable
fun FindSectionLightPreview() {
    AdoptmeTheme(darkTheme = false) {
        FindSection(modifier = Modifier.size(116.dp))
    }
}

@Preview
@Composable
fun FindSectionDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            FindSection(modifier = Modifier.size(116.dp))
        }
    }
}

@Preview
@Composable
fun PetCardLightPreview() {
    AdoptmeTheme {
        PetCard(Pet.fake, onPetClick = {}, onLikeClick = {})
    }
}

@Preview
@Composable
fun PetCardDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            PetCard(Pet.fake, onPetClick = {}, onLikeClick = {})
        }
    }
}