package cl.maqts.app.mislugares.ui.screens.authetication

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BiometricViewModel (application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(BiometricUiState())
    val uiState: StateFlow<BiometricUiState> = _uiState.asStateFlow()

    fun onAuthenticateClick() {
        Log.d("BiometricViewModel", "onAuthenticateClick llamado")
        resetAuthStatus()
        val availability = checkBiometricAvailability()
        if (availability == BiometricStatusDevice.AVAILABLE) {
            _uiState.update { it.copy(showPromptBiometri = it.showPromptBiometri + 1) }
        } else {
            onAvailabilityError(availability)
        }
    }

    fun onAuthenticationSucceeded() {
        _uiState.update { it.copy(authenticationSucceeded = true) }
    }

    fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        // seteo el error, excepto si el usuario canceló manualmente
        if (errorCode != androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON &&
            errorCode != androidx.biometric.BiometricPrompt.ERROR_USER_CANCELED
            ) {
            _uiState.update {
                it.copy(errorMessage = "Error de autenticación: $errString")
            }
        }
    }

    private fun resetAuthStatus() {
        _uiState.update {
            it.copy(errorMessage = null, authenticationSucceeded = false)
        }
    }

    fun checkBiometricAvailability(): BiometricStatusDevice {
        val context = getApplication<Application>().applicationContext

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return BiometricStatusDevice.NOT_AVAILABLE
        }

        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> BiometricStatusDevice.AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> BiometricStatusDevice.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> BiometricStatusDevice.TEMPORARILY_UNAVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> BiometricStatusDevice.AVAILABLE_BUT_NOT_ENROLLED
            else -> BiometricStatusDevice.NOT_AVAILABLE
        }
    }

    fun getBiometricErrorText(availability: BiometricStatusDevice): String {
        return when (availability) {
            BiometricStatusDevice.NOT_AVAILABLE -> "El dispositivo no soporta biometría."
            BiometricStatusDevice.TEMPORARILY_UNAVAILABLE -> "La biometría no está disponible en este momento. Inténtalo más tarde."
            BiometricStatusDevice.AVAILABLE_BUT_NOT_ENROLLED -> "Dispositivo no configurado para usar huella digital. Por favor, añádela en los ajustes de tu dispositivo."
            else -> "Ha ocurrido un error inesperado con la biometría."
        }
    }

    fun onAvailabilityError(availability: BiometricStatusDevice) {
        val errorMessage = getBiometricErrorText(availability)
        _uiState.update { it.copy(errorMessage = errorMessage) }
    }

}