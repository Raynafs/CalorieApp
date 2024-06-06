package  com.rachel.presentation.nutrients

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachel.data.CalorieRepository
import com.rachel.data.models.Calorie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutrientsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val repository: CalorieRepository
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _uiState: MutableStateFlow<NutrientsUiState> =
        MutableStateFlow(NutrientsUiState.Loading)
    val uiState: StateFlow<NutrientsUiState> get() = _uiState

    var fetchJob: Job? = null

    init {
        fetch()
    }

    fun fetch() {
        fetchJob?.cancel()
        _uiState.value = NutrientsUiState.Loading
        fetchJob = viewModelScope.launch {
            val calorie: Calorie = repository.getCalorie(name = id).first()
            _uiState.value = NutrientsUiState.Success(nutrient = calorie)
        }
    }

}