package org.example.bitrisedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Lógica compartida
object ValidadorCiCd {
    fun calcularDescuento(precio: Double, porcentaje: Double): Double {
        if (porcentaje < 0.0 || porcentaje > 100.0) {
            throw IllegalArgumentException("El porcentaje debe estar entre 0 y 100")
        }
        return precio - (precio * (porcentaje / 100.0))
    }

    fun estadoPipeline(): String = "VERIFICADO POR BITRISE CI/CD"
}

@Composable
fun App() {
    MaterialTheme {
        var mostrarResultado by remember { mutableStateOf(false) }
        // En un proyecto KMP sin ui-tooling completo, getPlatform().name puede fallar si no está implementado en tu plantilla.
        // Para evitar errores en la demo, lo simplificamos a una variable estática por ahora.
        val plataforma = "Android / iOS (KMP)"

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Demo CI/CD - Bitrise KMP",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ejecutando en: $plataforma", color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Resumen de Integración Continua", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Estado: ${ValidadorCiCd.estadoPipeline()}")
                    Text("• Módulo: shared (commonMain)")
                    Text("• Compilación de APK: Automatizada en la nube")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { mostrarResultado = !mostrarResultado }) {
                Text(if (mostrarResultado) "Ocultar prueba" else "Probar función validada en CI")
            }

            AnimatedVisibility(mostrarResultado) {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val precioFinal = ValidadorCiCd.calcularDescuento(250.0, 20.0)
                    Text(
                        text = "Cálculo exitoso (250 Bs - 20%): $precioFinal Bs",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF2E7D32)
                    )
                }
            }
        }
    }
}