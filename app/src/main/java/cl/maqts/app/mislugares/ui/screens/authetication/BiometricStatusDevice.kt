package cl.maqts.app.mislugares.ui.screens.authetication

/**
 * Enum para representar los posibles estados de la biometría en el dispositivo.
 * Valores:
 * - BiometricAvailability.AVAILABLE: dispositivo soporta biometria.
 * - BiometricAvailability.NOT_AVAILABLE: dispositivo no soporta biometria.
 * - BiometricAvailability.TEMPORARILY_UNAVAILABLE: La biometría no está disponible temporalmente.
 * - BiometricAvailability.AVAILABLE_BUT_NOT_ENROLLED: dispositivo no configurado para usar biometria.
 */
enum class BiometricStatusDevice {
    AVAILABLE,
    NOT_AVAILABLE,
    TEMPORARILY_UNAVAILABLE,
    AVAILABLE_BUT_NOT_ENROLLED
}