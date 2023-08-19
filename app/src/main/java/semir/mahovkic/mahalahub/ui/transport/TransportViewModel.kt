package semir.mahovkic.mahalahub.ui.transport

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TransportViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(TransportUiState())
    val uiState = _uiState.asStateFlow()
}