package cl.maqts.app.mislugares.ui.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
}