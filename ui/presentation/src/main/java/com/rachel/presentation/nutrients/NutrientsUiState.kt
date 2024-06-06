package  com.rachel.presentation.nutrients

import com.rachel.data.models.Calorie

sealed class NutrientsUiState {
    object Loading : NutrientsUiState()
    object Error : NutrientsUiState()
    data class Loaded(val nutrient: Calorie) : NutrientsUiState()
}