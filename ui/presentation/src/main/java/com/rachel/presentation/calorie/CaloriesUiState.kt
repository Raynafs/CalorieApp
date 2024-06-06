package  com.rachel.presentation.calorie
sealed class CaloriesUiState {
    object Loading : CaloriesUiState()
    data class Error(val message: String) : CaloriesUiState()
    object Empty : CaloriesUiState()
    object Success : CaloriesUiState()
}