package cl.maqts.app.mislugares.ui.screens.authetication

data class BiometricUiState(
    val errorMessage: String? = null,
    val authenticationSucceeded: Boolean = false,
    val showPromptBiometri: Int = 0
)