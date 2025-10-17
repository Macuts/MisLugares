package cl.maqts.app.mislugares

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import cl.maqts.app.mislugares.ui.navigation.AppNavigation
import cl.maqts.app.mislugares.ui.theme.AppTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(dynamicColor = false) {
                AppNavigation()
            }
        }
    }
}