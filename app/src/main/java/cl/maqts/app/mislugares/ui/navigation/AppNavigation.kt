package cl.maqts.app.mislugares.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.maqts.app.mislugares.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){

        composable(AppScreens.SplashScreen.route){
            SplashScreen()
        }
    }
}
