package com.chathil.adoptme.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.chathil.adoptme.ui.theme.AdoptmeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdoptmeScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable (() -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerConstants.DefaultElevation,
    drawerBackgroundColor: Color = AdoptmeTheme.colors.uiBackground,
    drawerContentColor: Color = AdoptmeTheme.colors.textSecondary,
    drawerScrimColor: Color = AdoptmeTheme.colors.uiBorder,
    backgroundColor: Color = AdoptmeTheme.colors.uiBackground,
    contentColor: Color = AdoptmeTheme.colors.textSecondary,
    bodyContent: @Composable (InnerPadding) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        bodyContent = bodyContent
    )
}