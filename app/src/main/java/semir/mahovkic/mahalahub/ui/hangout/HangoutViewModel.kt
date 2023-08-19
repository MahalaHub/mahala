package semir.mahovkic.mahalahub.ui.hangout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HangoutViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(HangoutUiState())
    val uiState = _uiState.asStateFlow()
}