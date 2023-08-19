package semir.mahovkic.mahalahub.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToCategory: (categoryId: Int) -> Unit,
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Button(onClick = { navigateToCategory(1) }) {
            Text(text = "Transports")
        }
        Button(onClick = { navigateToCategory(2) }) {
            Text(text = "Hangouts")
        }
    }
}