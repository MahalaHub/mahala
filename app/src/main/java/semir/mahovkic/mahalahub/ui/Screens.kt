package semir.mahovkic.mahalahub.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import semir.mahovkic.mahalahub.ui.composables.TopBar

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Home : Screens("home")
    object Transports : Screens("transports")
    object Hangouts : Screens("hangouts")
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            topBar = {
                if (!isLoginScreen(currentDestination)) {
                    TopBar(
                        onNavigationIconClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                AppDrawer(navController, drawerState, scope) {
                    MainNavGraph(
                        modifier = Modifier,
                        navController
                    )
                }
            }
        }
    }
}

fun isLoginScreen(currentDestination: NavDestination?): Boolean {
    return currentDestination?.route == Screens.Login.route
}