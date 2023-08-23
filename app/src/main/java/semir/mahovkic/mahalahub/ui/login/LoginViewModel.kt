package semir.mahovkic.mahalahub.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun generateLoginCode(username: String, emailOrPhoneNumber: String) {
        val confirmationCode = generateRandomString()
        _uiState.value = LoginUiState(username, emailOrPhoneNumber, confirmationCode)
    }

    fun clearLogin() {
        _uiState.value = LoginUiState()
    }

    private fun generateRandomString(length: Int = 6): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}