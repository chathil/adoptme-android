package com.chathil.adoptme.ui.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import com.chathil.adoptme.model.Pet
import com.chathil.adoptme.model.fake
import com.chathil.adoptme.model.image
import com.chathil.adoptme.ui.theme.AdoptmeTheme
import com.chathil.adoptme.ui.theme.AlphaNearTransparent
import com.example.jetsnack.ui.utils.SysUiController
import com.example.jetsnack.ui.utils.statusBarsPadding
import com.chathil.adoptme.R
import com.chathil.adoptme.model.icon
import com.chathil.adoptme.ui.components.AdoptmeScaffold
import com.chathil.adoptme.ui.components.Chip
import com.chathil.adoptme.ui.components.UpButton
import com.chathil.adoptme.ui.theme.padding
import java.util.*

@Composable
fun PetDetail(
    pet: Pet,
    upPress: () -> Unit
) {
    SysUiController.current.setStatusBarColor(
        AdoptmeTheme.colors.uiBackground.copy(
            AlphaNearTransparent
        )
    )
    AdoptmeScaffold {
        ScrollableColumn {
            Stack(modifier = Modifier.wrapContentHeight().padding(bottom = 32.dp)) {
                Image(
                    asset = imageResource(id = pet.image(ContextAmbient.current)),
                    modifier = Modifier.fillMaxWidth().height(296.dp),
                    contentScale = ContentScale.Crop
                )
                UpButton(upPress)
            }
            Body(pet = pet)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun Body(pet: Pet, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(horizontal = padding).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Column {
                Text(pet.name, style = MaterialTheme.typography.h4)
                Text(pet.location, style = MaterialTheme.typography.caption)
            }
            PetSize(pet = pet)
        }
        Row(
            modifier = Modifier.padding(horizontal = padding).padding(top = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalGravity = Alignment.CenterVertically
        ) {
            Row {
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
                Chip(content = {
                    Text(
                        if (pet.isMale) "Male" else "Female",
                        color = AdoptmeTheme.colors.primary
                    )
                })
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    asset = Icons.Rounded.Favorite,
                    tint = AdoptmeTheme.colors.btnLike,
                )
            }
        }
        Text(
            pet.desc,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = padding).padding(top = 32.dp)
        )
    }
}

@Composable
private fun PetSize(
    pet: Pet,
    modifier: Modifier = Modifier,
    colorActive: Color = AdoptmeTheme.colors.primary,
    colorInactive: Color = AdoptmeTheme.colors.textPrimary
) {
    Row(
        modifier = modifier.wrapContentSize(Alignment.BottomStart),
        verticalGravity = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            asset = imageResource(id = pet.icon(ContextAmbient.current)),
            Modifier.size(24.dp).padding(bottom = 1.dp),
            tint = if (pet.size == Pet.Size.S) colorActive else colorInactive
        )
        Icon(
            asset = imageResource(id = pet.icon(ContextAmbient.current)),
            Modifier.size(32.dp).padding(bottom = 1.dp),
            tint = if (pet.size == Pet.Size.M) colorActive else colorInactive
        )
        Icon(
            asset = imageResource(id = pet.icon(ContextAmbient.current)),
            Modifier.size(40.dp),
            tint = if (pet.size == Pet.Size.L) colorActive else colorInactive
        )
    }
}

@Preview
@Composable
fun PetDetailPreview() {
    AdoptmeTheme {
        PetDetail(Pet.fake, {})
    }
}

@Preview
@Composable
fun PetSizePreview() {
    PetSize(pet = Pet.fake)
}