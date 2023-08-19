package semir.mahovkic.mahalahub.ui.hangout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import semir.mahovkic.mahalahub.ui.composables.TopBar

@Composable
fun HangoutScreen(
    navController: NavController,
    categoryId: Int,
    viewModel: HangoutViewModel
) {
    val uiState: HangoutUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(text = "Hangout screen")
    }
}