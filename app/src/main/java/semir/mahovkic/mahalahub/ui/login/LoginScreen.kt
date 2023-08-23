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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import semir.mahovkic.mahalahub.ui.composables.AppName
import semir.mahovkic.mahalahub.ui.composables.LogoImage

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    val uiState: LoginUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LogoHeader(
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.TopCenter)
        )

        if (uiState.confirmationCode.trim().isEmpty()) {
            LoginForm(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp, top = 100.dp)
                    .align(Alignment.Center)
            ) { username, emailOrPhoneNumber ->
                viewModel.generateLoginCode(username, emailOrPhoneNumber)
            }
        } else {
            ConfirmationCodeForm(
                uiState.confirmationCode,
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp, top = 100.dp)
                    .align(Alignment.Center)
            ) {
                viewModel.clearLogin()
                navigateToHome()
            }
        }
    }
}

@Composable
fun LogoHeader(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        LogoImage()
        AppName(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    onClick: (username: String, emailOrPhoneNumber: String) -> Unit
) {
    val username = remember { mutableStateOf("") }
    val emailOrPhoneNumber = remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        UsernameField(
            username,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        EmailField(
            emailOrPhoneNumber,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(
            username.value,
            emailOrPhoneNumber.value,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick
        )
    }
}

@Composable
fun UsernameField(username: MutableState<String>, modifier: Modifier) {
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
        textStyle = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun EmailField(emailOrPhoneNumber: MutableState<String>, modifier: Modifier) {
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
        textStyle = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun LoginButton(
    username: String,
    emailOrPhoneNumber: String,
    modifier: Modifier,
    onClick: (username: String, emailOrPhoneNumber: String) -> Unit
) {
    val loginEnabled =
        username.trim().isNotEmpty() && emailOrPhoneNumber.trim().isNotEmpty()

    Button(
        enabled = loginEnabled,
        modifier = modifier,
        onClick = {
            onClick(username, emailOrPhoneNumber)
        }
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Prijavi se",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun ConfirmationCodeForm(
    generatedConfirmationCode: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val confirmationCode = remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = confirmationCode.value,
            onValueChange = { confirmationCode.value = it },
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Unesi kod",
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            textStyle = MaterialTheme.typography.titleMedium
        )

        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            text = generatedConfirmationCode,
            style = MaterialTheme.typography.labelLarge
        )

        ConfirmLoginButton(generatedConfirmationCode, confirmationCode.value, modifier, onClick)
    }
}

@Composable
fun ConfirmLoginButton(
    generatedConfirmationCode: String,
    confirmationCode: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        enabled = confirmationCode.trim()
            .isNotEmpty() && generatedConfirmationCode == confirmationCode,
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Potvrdi prijavu",
            style = MaterialTheme.typography.titleLarge
        )
    }
}