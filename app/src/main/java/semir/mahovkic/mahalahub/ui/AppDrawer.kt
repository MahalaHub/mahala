package semir.mahovkic.mahalahub.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            ModalDrawerSheet {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (!isLoginScreen(currentDestination)) {
                            MainScreenDrawerContent(navController, drawerState, scope)
                        }
                    }
                }
            }
        }) {
        appContent()
    }
}

@Composable
fun MainScreenDrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Button(onClick = {
        scope.launch {
            drawerState.close()
        }
        navController.navigate(Screens.Home.route)
    }) {
        Text(text = "Home")
    }

    Button(onClick = {
        scope.launch {
            drawerState.close()
        }
        navController.navigate(Screens.Login.route)
    }) {
        Text(text = "Logout")
    }
}