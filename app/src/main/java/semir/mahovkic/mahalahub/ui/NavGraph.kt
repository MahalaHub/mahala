package semir.mahovkic.mahalahub.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import semir.mahovkic.mahalahub.ui.hangout.HangoutScreen
import semir.mahovkic.mahalahub.ui.hangout.HangoutViewModel
import semir.mahovkic.mahalahub.ui.home.HomeScreen
import semir.mahovkic.mahalahub.ui.home.HomeViewModel
import semir.mahovkic.mahalahub.ui.login.LoginScreen
import semir.mahovkic.mahalahub.ui.login.LoginViewModel
import semir.mahovkic.mahalahub.ui.transport.TransportScreen
import semir.mahovkic.mahalahub.ui.transport.TransportViewModel

@Composable
fun MainNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    transportViewModel: TransportViewModel = viewModel(),
    hangoutViewModel: HangoutViewModel = viewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        loginScreen(navController, loginViewModel, this)
        homeScreen(navController, homeViewModel, this)
        transportScreen(navController, transportViewModel, this)
        hangoutScreen(navController, hangoutViewModel, this)
    }
}

private fun loginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(Screens.Login.route) {
        LoginScreen(loginViewModel) {
            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.Login.route) {
                    inclusive = true
                }
            }
        }
    }
}

private fun homeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(Screens.Home.route) {
        HomeScreen(homeViewModel,
            navigateToCategory = {
                when (it) {
                    1 -> navController.navigate("${Screens.Transports.route}/${it}")
                    2 -> navController.navigate("${Screens.Hangouts.route}/${it}")
                }
            })
    }
}

private fun transportScreen(
    navController: NavHostController,
    transportViewModel: TransportViewModel,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        "${Screens.Transports.route}/{categoryId}",
        arguments = listOf(
            navArgument("categoryId") { type = NavType.IntType },
        )
    ) {
        TransportScreen(
            navController,
            it.arguments?.getInt("categoryId") ?: 0,
            transportViewModel
        )
    }
}

private fun hangoutScreen(
    navController: NavHostController,
    hangoutViewModel: HangoutViewModel,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        "${Screens.Hangouts.route}/{categoryId}",
        arguments = listOf(
            navArgument("categoryId") { type = NavType.IntType },
        )
    ) {
        HangoutScreen(
            navController,
            it.arguments?.getInt("categoryId") ?: 0,
            hangoutViewModel
        )
    }
}