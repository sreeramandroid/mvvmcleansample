package com.rs.tmobiledemosample.presentation.viewmodel// viewmodel/StudentViewModel.kt
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rs.tmobiledemosample.data.model.Student
import com.rs.tmobiledemosample.domain.usecase.GetStudentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import androidx.compose.runtime.State

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase
) : ViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _selectedTab = mutableStateOf("Activity")
    val selectedTab: State<String> = _selectedTab

    fun onTabSelected(tab: String) {
        _selectedTab.value = tab
    }

    init {
        fetchAndStoreStudents()
    }

    private fun fetchAndStoreStudents() {
        viewModelScope.launch {
            getStudentsUseCase.fetchAndStoreStudents()
                .onEach { result ->
                    result.onFailure { _error.value = it.localizedMessage }
                }
                .launchIn(this)
        }

        loadLocalStudents()
    }

    private fun loadLocalStudents() {
        viewModelScope.launch {
            getStudentsUseCase.getLocalStudents()
                .onEach { _students.value = it }
                .launchIn(this)
        }
    }
}