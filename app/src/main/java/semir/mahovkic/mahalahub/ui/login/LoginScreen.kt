package semir.mahovkic.mahalahub.ui.login

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import semir.mahovkic.mahalahub.ui.composables.AppName
import semir.mahovkic.mahalahub.ui.composables.LogoImage

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessageState by viewModel.errorMessage.collectAsStateWithLifecycle()

    ShowErrorMessage(errorMessageState) {
        viewModel.clearErrorMessage()
    }

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
            ) { username, email ->
                viewModel.generateLoginCode(username, email)
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
fun ShowErrorMessage(message: String, callback: () -> Unit) {
    if (message.isNotEmpty()) {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchedEffect(key1 = message) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Short
                        )
                        callback()
                    }
                }
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
    onClick: (username: String, email: String) -> Unit
) {
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

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
            email,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(
            username.value,
            email.value,
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
fun EmailField(email: MutableState<String>, modifier: Modifier) {
    OutlinedTextField(
        value = email.value,
        onValueChange = { email.value = it },
        shape = RoundedCornerShape(30.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = "Email adresa",
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
    email: String,
    modifier: Modifier,
    onClick: (username: String, email: String) -> Unit
) {
    val loginEnabled =
        username.trim().isNotEmpty() && email.trim().isNotEmpty()

    Button(
        enabled = loginEnabled,
        modifier = modifier,
        onClick = {
            onClick(username, email)
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
        enabled = confirmationCode.trim().isNotEmpty() &&
                generatedConfirmationCode == confirmationCode,
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