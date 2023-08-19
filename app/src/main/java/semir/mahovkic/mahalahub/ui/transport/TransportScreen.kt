package semir.mahovkic.mahalahub.ui.transport

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import semir.mahovkic.mahalahub.ui.composables.TopBar

@Composable
fun TransportScreen(
    navController: NavController,
    categoryId: Int,
    viewModel: TransportViewModel
) {
    val uiState: TransportUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(text = "Transports screen")
    }
}