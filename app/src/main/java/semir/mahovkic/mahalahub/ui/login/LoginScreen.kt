package semir.mahovkic.mahalahub.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import semir.mahovkic.mahalahub.ui.composables.EmptySearchBy
import semir.mahovkic.mahalahub.ui.composables.TopBar

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    val uiState: LoginUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchBy = remember { mutableStateOf(EmptySearchBy) }

    Column {
        Button(onClick = navigateToHome) {
            Text(text = "Login")
        }
    }
}