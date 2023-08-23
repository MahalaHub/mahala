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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LogoHeader(
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.TopCenter)
        )

        LoginForm(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 100.dp)
                .align(Alignment.Center),
            navigateToHome
        )
    }
}

@Composable
fun LogoHeader(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        LogoImage(Modifier)

        Text(
            text = "Mahala!",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun LoginForm(modifier: Modifier, onClick: () -> Unit) {
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
    onClick: () -> Unit
) {
    val loginEnabled =
        username.trim().isNotEmpty() && emailOrPhoneNumber.trim().isNotEmpty()

    Button(
        enabled = loginEnabled,
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Prijavi se",
            style = MaterialTheme.typography.titleLarge
        )
    }
}