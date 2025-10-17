package cl.maqts.app.mislugares.ui.screens.authetication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.maqts.app.mislugares.R

@Preview(showBackground = true, name = "Dispositivo con biometria")
@Composable
fun BiometricAuthScreenPreview() {
    AuthScreenContent(onAuthenticateClick = {}, errorMessage = null)
}

@Preview(showBackground = true, name = "Dispositivo sin biometria")
@Composable
fun BiometricAuthErrorPreview() {
    AuthScreenContent(
        errorMessage = "Tu dispositivo no soporta biometría.",
        onAuthenticateClick = {}
    )
}

@Composable
fun AuthScreenContent(
    errorMessage: String?,
    onAuthenticateClick: () -> Unit
) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8F5E9))
                .padding(26.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_huella_dactilar),
                contentDescription = "Icono de huella dactilar",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Autenticación Biométrica",
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onAuthenticateClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF476F59),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Autenticarme", fontSize = 16.sp)
            }

            // Muestro el mensaje de error si existe
            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = errorMessage,
                    color = Color(0xFFD32F2F),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
}
