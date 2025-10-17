package cl.maqts.app.mislugares.ui.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object BiometricAuthScreen : AppScreens("biometric_auth_screen")
    object HomeScreen : AppScreens("home_screen")

}