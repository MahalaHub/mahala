package semir.mahovkic.mahalahub.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import semir.mahovkic.mahalahub.data.UsersRepository
import javax.inject.Inject

const val GeneralErrorMessage = "Greška u zahtjevu!"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    companion object {
        val Errors = mapOf(
            "username already exists" to "Korisničko ime već postoji!"
        )
    }

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun generateLoginCode(username: String, email: String) {
        viewModelScope.launch {
            try {
                val loginDetails = usersRepository.generateLoginCode(username, email)
                _uiState.value = LoginUiState(username, email, loginDetails.code)
            } catch (e: HttpException) {
                val err = e.response()?.errorBody()?.string()?.let {
                    JSONObject(it)
                }?.getString("message").toString()

                _errorMessage.value = Errors[err] ?: GeneralErrorMessage
            }
        }
    }

    fun clearLogin() {
        _uiState.value = LoginUiState()
    }

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }
}