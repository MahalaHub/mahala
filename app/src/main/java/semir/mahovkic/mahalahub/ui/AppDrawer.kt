package semir.mahovkic.mahalahub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import semir.mahovkic.mahalahub.ui.composables.AppName
import semir.mahovkic.mahalahub.ui.composables.IconImage
import semir.mahovkic.mahalahub.ui.composables.LogoImage

@Composable
fun AppDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
    appContent: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = !isLoginScreen(currentDestination),
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(250.dp)
            ) {
                DrawerMainContent(navController, drawerState, scope)
            }
        }) {
        appContent()
    }
}

@Composable
fun DrawerMainContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val drawerItems = listOf(
        DrawerItem(icon = Icons.Filled.Settings, title = "Podešavanja") {
            navController.navigate(Screens.Drawer.Settings.route)
        },
        DrawerItem(icon = Icons.Filled.ArrowBack, title = "Odjavi se") {
            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = true
                }
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DrawerHeader(modifier = Modifier.align(Alignment.TopStart)) {
            scope.launch {
                drawerState.close()
            }
            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.Home.route) {
                    inclusive = true
                }
            }
        }
        DrawerMain()
        DrawerFooter(
            drawerState,
            scope,
            drawerItems,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .clickable { onClick() }
        ) {
            LogoImage(
                modifier = Modifier.align(Alignment.CenterVertically),
                size = 50.dp
            )
            AppName(Modifier.align(Alignment.CenterVertically))
        }
        Spacer(
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )
    }
}

@Composable
fun DrawerMain() {

}

@Composable
fun DrawerFooter(
    drawerState: DrawerState,
    scope: CoroutineScope,
    drawerItems: List<DrawerItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        LazyColumn {
            items(drawerItems) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    DrawerItem(drawerState, scope, it)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun DrawerItem(
    drawerState: DrawerState,
    scope: CoroutineScope,
    item: DrawerItem
) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable {
                scope.launch {
                    drawerState.close()
                }
                item.onClick()
            }
    ) {
        IconImage(
            modifier = Modifier.align(Alignment.CenterVertically),
            icon = item.icon
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = item.title, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

data class DrawerItem(
    val icon: ImageVector,
    val title: String = "",
    val onClick: () -> Unit = {}
)