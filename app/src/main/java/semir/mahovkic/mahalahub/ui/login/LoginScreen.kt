package semir.mahovkic.mahalahub.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import semir.mahovkic.mahalahub.ui.composables.EmptySearchBy
import semir.mahovkic.mahalahub.ui.composables.LogoImage

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    val uiState: LoginUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchBy = remember { mutableStateOf(EmptySearchBy) }
    val username = remember { mutableStateOf("") }
    val emailOrPhoneNumber = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.TopCenter)
        ) {
            LogoImage(
                Modifier
            )

            Text(
                text = "Mahala!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 100.dp)
                .align(Alignment.Center)
        ) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Izaberi korisničko ime",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textStyle = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = emailOrPhoneNumber.value,
                onValueChange = { emailOrPhoneNumber.value = it },
                shape = RoundedCornerShape(30.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Broj telefona ili email adresa",
                        color = MaterialTheme.colorScheme.outline,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textStyle = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(20.dp))

            val loginEnabled =
                username.value.trim().isNotEmpty() && emailOrPhoneNumber.value.trim().isNotEmpty()

            Button(
                enabled = loginEnabled,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = navigateToHome
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Login",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
