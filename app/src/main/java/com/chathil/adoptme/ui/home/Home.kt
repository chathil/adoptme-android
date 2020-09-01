package com.chathil.adoptme.ui.home


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.chathil.adoptme.R
import com.chathil.adoptme.model.*
import com.chathil.adoptme.ui.components.*
import com.chathil.adoptme.ui.theme.AdoptmeTheme
import com.chathil.adoptme.ui.theme.AlphaNearTransparent
import com.chathil.adoptme.ui.theme.padding
import com.example.jetsnack.ui.utils.SysUiController
import com.example.jetsnack.ui.utils.statusBarsPadding
import java.util.*

@Composable
fun Home(onPetSelected: (Pet) -> Unit, onAccountClicked: () -> Unit) {
    SysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    AdoptmeScaffold { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        ScrollableColumn(modifier = modifier.padding(horizontal = padding)) {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .preferredHeight(16.dp)
            )
            AccountSection(name = "Chathil", modifier) { onAccountClicked() }
            FindSection(modifier = modifier)
            Spacer(modifier = Modifier.height(16.dp))
            Pet.allPets(ContextAmbient.current)?.forEach {
                PetCard(pet = it) { pet ->
                    onPetSelected(pet)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
private fun AccountSection(name: String, modifier: Modifier = Modifier, onAccountClicked: () -> Unit) {
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
                Spacer(modifier = Modifier.preferredHeight(8.dp))
                AdoptmeButton(onClick = onAccountClicked) {
                    Row {
                        ProvideTextStyle(value = MaterialTheme.typography.body1) {
                            Text("Account")
                        }
                        Spacer(modifier = Modifier.preferredWidth(8.dp))
                        Icon(
                            asset = Icons.Rounded.ChevronRight,
                            tint = AdoptmeTheme.colors.btnContent,
                        )
                    }
                }
            }
            Spacer(Modifier.preferredWidth(16.dp))
            CircleImage(modifier = Modifier.size(116.dp), imageResource(id = R.drawable.me))
        }
        AccountCompletion(modifier = modifier)
    }
}

@Composable
private fun AccountCompletion(modifier: Modifier = Modifier) {
    Text("Complete Your Profile", modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
    AdoptmeProgressBar(progress = 0.7f, modifier = Modifier.preferredWidth(216.dp))
}

@Composable
private fun FindSection(modifier: Modifier = Modifier) {
    Row(verticalGravity = Alignment.CenterVertically, modifier = modifier) {
        AdoptmeSurface(
            color = Color.Transparent,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Image(asset = imageResource(id = R.drawable.ill_cat), modifier = Modifier.width(116.dp))
        }
        Spacer(modifier = Modifier.preferredWidth(16.dp))
        Column(modifier = Modifier.fillMaxHeight().wrapContentHeight()) {
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
private fun PetCard(pet: Pet, modifier: Modifier = Modifier, onPetClick: (Pet) -> Unit) {
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Row(
        modifier = modifier.fillMaxWidth().height(116.dp).clickable(onClick = { onPetClick(pet) })
    ) {
        AdoptmeSurface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.size(116.dp),
            shape = RoundedCornerShape(
                topLeft = 16.dp,
                bottomLeft = 16.dp,
                topRight = 0.dp,
                bottomRight = 0.dp
            )
        ) {
            Image(
                asset = imageResource(id = pet.image(ContextAmbient.current)),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.preferredWidth(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
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
                verticalGravity = Alignment.CenterVertically
            ) {
                Chip(
                    start = {
                        Icon(
                            asset = imageResource(
                                id = pet.icon(ContextAmbient.current)
                            ),
                            tint = AdoptmeTheme.colors.primary,
                            modifier = Modifier.size(16.dp)
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
                    onClick = { }
                ) {
                    Icon(
                        asset = Icons.Rounded.Favorite,
                        tint = AdoptmeTheme.colors.btnLike,
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Divider(
        modifier = Modifier.padding(start = 124.dp),
        color = AdoptmeTheme.colors.primaryVariant.copy(
            AlphaNearTransparent
        )
    )
}

@Preview
@Composable
fun HomePreview() {
    AdoptmeTheme(darkTheme = true) {
        Home({}, {})
    }
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
        PetCard(Pet.fake) {}
    }
}

@Preview
@Composable
fun PetCardDarkPreview() {
    AdoptmeTheme(darkTheme = true) {
        AdoptmeSurface {
            PetCard(Pet.fake) {}
        }
    }
}