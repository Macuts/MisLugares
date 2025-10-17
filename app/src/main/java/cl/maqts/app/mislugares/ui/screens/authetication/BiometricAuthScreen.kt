package cl.maqts.app.mislugares.ui.screens.authetication

import android.util.Log
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BiometricAuthScreen(
    onAuthSuccess: () -> Unit,
    viewModel: BiometricViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity
    val executor = remember { ContextCompat.getMainExecutor(context) }
    val uiState by viewModel.uiState.collectAsState()

    // Construyo el BiometricPrompt (Modal del sistema para la biometría)
    val biometricPrompt = remember(activity, executor, viewModel) {
        activity?.let {
            BiometricPrompt(it, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    viewModel.onAuthenticationSucceeded()
                }
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    viewModel.onAuthenticationError(errorCode, errString)
                }
            })
        }
    }

    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación Requerida")
            .setSubtitle("Usa tu huella para acceder a Mis Lugares")
            .setAllowedAuthenticators(BIOMETRIC_STRONG) // o BIOMETRIC_STRONG or DEVICE_CREDENTIAL
            .setNegativeButtonText("Cancelar")
            .build()
    }

    // LaunchedEffect para el evento de navegación
    LaunchedEffect(uiState.authenticationSucceeded) {
        if (uiState.authenticationSucceeded) {
            onAuthSuccess() // evento de autenticación exitosa
        }
    }

    // LaunchedEffect para el evento de mostrar el diálogo
    LaunchedEffect(uiState.showPromptBiometri) {
        if (uiState.showPromptBiometri > 0) {
            biometricPrompt?.authenticate(promptInfo)
        }
    }

    // Lanzar la autenticación automáticamente la primera vez
    LaunchedEffect(key1 = activity) {
        if (activity != null){
            viewModel.onAuthenticateClick()
            Log.d("BiometricAuthScreen", "Autenticación automática lanzada")
        }
    }

    AuthScreenContent(
        errorMessage = uiState.errorMessage,
        onAuthenticateClick = { viewModel.onAuthenticateClick() }
    )
}
