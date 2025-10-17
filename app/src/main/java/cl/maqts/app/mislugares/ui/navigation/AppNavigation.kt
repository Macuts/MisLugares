package cl.maqts.app.mislugares.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.maqts.app.mislugares.ui.screens.authetication.BiometricAuthScreen
import cl.maqts.app.mislugares.ui.screens.home.HomeScreen
import cl.maqts.app.mislugares.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){

        composable(AppScreens.SplashScreen.route){
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(AppScreens.BiometricAuthScreen.route){
                        popUpTo(AppScreens.SplashScreen.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppScreens.BiometricAuthScreen.route){
            BiometricAuthScreen(
                onAuthSuccess = {
                    navController.navigate(AppScreens.HomeScreen.route){
                        popUpTo(AppScreens.BiometricAuthScreen.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(AppScreens.HomeScreen.route){
            HomeScreen()
        }
    }
}
