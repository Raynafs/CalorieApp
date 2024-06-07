/*
 * Copyright 2021-2024 The Calorie App Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rachel.presentation.calorie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rachel.domain.CalorieRepository
import com.rachel.domain.models.Calorie
import com.rachel.domain.models.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CaloriesViewModel @Inject constructor(private val repository: CalorieRepository) :
    ViewModel() {

    private val _query = MutableStateFlow("")
    val query: Flow<String>
        get() = _query

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean>
        get() = _isLoading

    private val _hasError = MutableStateFlow<String?>(null)
    val hasError: Flow<String?>
        get() = _hasError

    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: Flow<Boolean>
        get() = _isEmpty

    private val _state: MutableStateFlow<CaloriesUiState> = MutableStateFlow(CaloriesUiState.Loading)
    val state: StateFlow<CaloriesUiState>
        get() = _state

    private val _calories = _query.flatMapLatest { repository.getCalories() }
    val calories: Flow<List<Calorie>>
        get() = _calories

    private var searchJob: Job? = null
    private var hasAlreadyLoadedOnce: Boolean = false

    init {
        viewModelScope.launch {
            if (calories.first().isEmpty().not()) {
                updateState(CaloriesUiState.Success)
            }
        }
    }

    fun updateQuery(str: String) {
        _query.value = str
        hideEmpty()
    }

    fun search() {
        searchJob?.cancel()
        searchJob =
            viewModelScope.launch {
                showLoading()
                val resource = repository.searchCalories(query = _query.value)
                hideLoading()
                when (resource) {
                    is Resource.Error -> showError(resource = resource)
                    is Resource.Success -> showSuccess(resource = resource)
                }
            }
    }

    private fun showSuccess(resource: Resource.Success) {
        when {
            resource.isEmpty -> {
                if (hasAlreadyLoadedOnce) {
                    _isEmpty.value = true
                } else {
                    updateState(CaloriesUiState.Empty)
                }
            }
            else -> {
                updateState(CaloriesUiState.Success)
                hasAlreadyLoadedOnce = true
            }
        }
    }

    fun hideEmpty() {
        _isEmpty.value = false
    }

    private fun showError(resource: Resource.Error) {
        when (hasAlreadyLoadedOnce) {
            true -> _hasError.value = resource.message
            false -> updateState(CaloriesUiState.Error(resource.message))
        }
    }

    fun hideError() {
        _hasError.value = null
    }

    private fun showLoading() {
        when (hasAlreadyLoadedOnce) {
            true -> _isLoading.value = true
            false -> updateState(CaloriesUiState.Loading)
        }
    }

    private fun hideLoading() {
        if (hasAlreadyLoadedOnce) {
            _isLoading.value = false
        }
    }

    private fun updateState(updateState: CaloriesUiState) {
        _state.value = updateState
    }
}
