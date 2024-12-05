package com.rs.tmobiledemosample.authmodule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.rs.tmobiledemosample.presentation.viewmodel.AuthViewModel
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class AuthViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AuthViewModel
    // Create a test dispatcher
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
        viewModel = spyk(AuthViewModel())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }

    @Test
    fun `when phone number is valid and password is valid, isFormValid should be true`() = runTest {
        viewModel.onPhoneNumberChange("1234567890") // Valid phone number
        viewModel.onPasswordChange("Password1!") // Valid password
        advanceUntilIdle()
        viewModel.isFormValid.test {
            assertTrue(awaitItem()) // Assert that isFormValid is true
        }
        //assertEquals(true,viewModel.isFormValid.value==false)
    }

    @Test
    fun `when phone number is invalid, isFormValid should be false`() = runTest {
        viewModel.onPhoneNumberChange("12345") // Invalid phone number
        viewModel.onPasswordChange("Password1!") // Valid password
        advanceUntilIdle()
        viewModel.isFormValid.test {
            assertFalse(awaitItem()) // Assert that isFormValid is false
        }
    }

    @Test
    fun `when password is invalid, isFormValid should be false`() = runTest {
        viewModel.onPhoneNumberChange("1234567890") // Valid phone number
        viewModel.onPasswordChange("pass") // Invalid password (too short, no special character or digit)
        advanceUntilIdle()
        viewModel.isFormValid.test {
            assertFalse(awaitItem()) // Assert that isFormValid is false
        }
    }

    @Test
    fun `onPhoneNumberChange should update phone number if input is numeric`() = runTest {
        viewModel.onPhoneNumberChange("1234567890")
        advanceUntilIdle()
        viewModel.phoneNumber.test {
            assertEquals("1234567890", awaitItem()) // Assert that phoneNumber is updated correctly
        }
    }

    @Test
    fun `onPhoneNumberChange should not update phone number if input is non-numeric`() = runTest {
        viewModel.onPhoneNumberChange("abcdef")
        advanceUntilIdle()
        viewModel.phoneNumber.test {
            assertEquals("", awaitItem()) // Assert that phoneNumber remains empty
        }
    }

    @Test
    fun `onPasswordChange should update password`() = runTest {
        viewModel.onPasswordChange("Password1!")
        advanceUntilIdle()
        viewModel.password.test {
            assertEquals("Password1!", awaitItem()) // Assert that password is updated correctly
        }
    }
}
