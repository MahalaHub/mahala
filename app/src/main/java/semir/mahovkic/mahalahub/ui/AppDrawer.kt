package semir.mahovkic.mahalahub.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
                MainScreenDrawerContent(navController, drawerState, scope)
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
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier

                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }

                }) {
                Text(text = "Settings")
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Home.route) {
                            inclusive = true
                        }
                    }
                }) {
                Text(text = "Logout")
            }
        }
    }
}