package semir.mahovkic.mahalahub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import semir.mahovkic.mahalahub.R

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
                MainDrawerContent(navController, drawerState, scope)
            }
        }) {
        appContent()
    }
}

@Composable
fun MainDrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val drawerItems = listOf(
        DrawerItem(title = "Podešavanja") {
            navController.navigate(Screens.Drawer.Settings.route)
        },
        DrawerItem(title = "Odjavi se") {
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
        LazyColumn {
            items(drawerItems) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            it.onClick()
                        }
                    ) {
                        Text(text = it.title)
                    }
                }
            }
        }
    }
}

data class DrawerItem(
    val icon: Int = R.mipmap.logo,
    val title: String = "",
    val onClick: () -> Unit = {}
)