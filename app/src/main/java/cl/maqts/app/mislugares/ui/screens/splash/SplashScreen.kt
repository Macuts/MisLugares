package cl.maqts.app.mislugares.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.maqts.app.mislugares.R
import cl.maqts.app.mislugares.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    AppTheme {
        SplashScreen()
    }
}


@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit = {}
) {
    LaunchedEffect(key1 = Unit) {
        delay(1500)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8F5E9)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mis Lugares",
                color = Color(0xFF476F59),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold            )
            Image(
                painter = painterResource(id = R.drawable.logo_my_places),
                contentDescription = "Logo Mis Lugares",
                modifier = Modifier
                    .size(430.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            // Indicador de carga
            CircularProgressIndicator(
                color = Color(0xFF476F59), // verde similar al del globo
                strokeWidth = 6.dp,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}