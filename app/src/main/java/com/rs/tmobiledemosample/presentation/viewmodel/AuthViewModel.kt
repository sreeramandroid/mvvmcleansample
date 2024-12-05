package com.rs.tmobiledemosample.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {

    // State flows for phone number and password
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    // State flow to track form validity
    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid

    init {
        // Combine phone number and password flows to update form validity
        viewModelScope.launch {
            combine(phoneNumber, password) { phone, pass ->
                isPhoneNumberValid(phone) && isPasswordValid(pass)
            }.collect { valid ->
                _isFormValid.value = valid
            }
        }
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        if (newPhoneNumber.all { it.isDigit() }) {
            _phoneNumber.value = newPhoneNumber.take(10) // Limit to 10 digits
        }
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    private fun isPhoneNumberValid(phone: String): Boolean {
        return phone.length == 10
    }

    private fun isPasswordValid(password: String): Boolean {
        // Regex explanation:
        // (?=.*[0-9]) - At least one digit
        // (?=.*[!@#$%^&*()_+{}|:;'<>,.?/~`-]) - At least one special character
        // .{8,} - Minimum length of 8 characters
        val passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+{}|:;'<>,.?/~`-]).{8,}$"
        return password.isNotEmpty() && Regex(passwordPattern).matches(password)
    }
}
