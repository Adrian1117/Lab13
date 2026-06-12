package com.example.lab13.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Los tres estados de la aplicación
enum class AppState { LOADING, CONTENT, ERROR }

@Composable
fun Exercise4Screen() {

    // Estado actual de la app
    var currentState by remember { mutableStateOf(AppState.LOADING) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C2E))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        // Título
        Text(
            text = "Ejercicio 4",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "AnimatedContent",
            fontSize = 16.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // Área principal con AnimatedContent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    color = Color(0xFF2A2A3E),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = currentState,
                transitionSpec = {
                    // Entrada: fadeIn desde abajo
                    // Salida: fadeOut hacia arriba
                    (fadeIn(animationSpec = tween(600)) +
                            slideInVertically(
                                animationSpec = tween(600),
                                initialOffsetY = { it / 2 }
                            )).togetherWith(
                        fadeOut(animationSpec = tween(400)) +
                                slideOutVertically(
                                    animationSpec = tween(400),
                                    targetOffsetY = { -it / 2 }
                                )
                    )
                },
                label = "appStateAnimation"
            ) { state ->
                when (state) {
                    AppState.LOADING -> LoadingContent()
                    AppState.CONTENT -> SuccessContent()
                    AppState.ERROR   -> ErrorContent()
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Indicador de estado activo
        StateIndicator(currentState = currentState)

        Spacer(modifier = Modifier.height(24.dp))

        // Botones para cambiar de estado
        Text(
            text = "Cambiar estado:",
            fontSize = 14.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Botón LOADING
            StateButton(
                label = "⏳ Cargando",
                isActive = currentState == AppState.LOADING,
                activeColor = Color(0xFF7C4DFF),
                modifier = Modifier.weight(1f),
                onClick = { currentState = AppState.LOADING }
            )

            // Botón CONTENT
            StateButton(
                label = "✅ Contenido",
                isActive = currentState == AppState.CONTENT,
                activeColor = Color(0xFF2E7D32),
                modifier = Modifier.weight(1f),
                onClick = { currentState = AppState.CONTENT }
            )

            // Botón ERROR
            StateButton(
                label = "❌ Error",
                isActive = currentState == AppState.ERROR,
                activeColor = Color(0xFFC62828),
                modifier = Modifier.weight(1f),
                onClick = { currentState = AppState.ERROR }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta informativa
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "💡 ¿Cómo funciona?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C4DFF)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "AnimatedContent detecta el cambio de estado y aplica\n" +
                            "fadeIn + slideIn al nuevo contenido mientras el anterior\n" +
                            "sale con fadeOut + slideOut simultáneamente.",
                    fontSize = 12.sp,
                    color = Color(0xFF8080A0),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ─────────────────────────────────────────────
// Composables auxiliares para cada estado
// ─────────────────────────────────────────────

@Composable
fun LoadingContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(text = "⏳", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Cargando...",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C4DFF)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Por favor espera mientras\nobtenemos la información",
            fontSize = 13.sp,
            color = Color(0xFF8080A0),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(4.dp),
            color = Color(0xFF7C4DFF),
            trackColor = Color(0xFF3A3A4E)
        )
    }
}

@Composable
fun SuccessContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(text = "✅", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¡Contenido listo!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Los datos se cargaron\ncorrectamente",
            fontSize = 13.sp,
            color = Color(0xFF8080A0),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF1B5E20),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "200 OK",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF81C784)
            )
        }
    }
}

@Composable
fun ErrorContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    ) {
        Text(text = "❌", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ocurrió un error",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE53935)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "No se pudo obtener\nla información",
            fontSize = 13.sp,
            color = Color(0xFF8080A0),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF7F0000),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "500 Server Error",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFEF9A9A)
            )
        }
    }
}

// Botón reutilizable para los estados
@Composable
fun StateButton(
    label: String,
    isActive: Boolean,
    activeColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActive) activeColor else Color(0xFF2A2A3E)
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color.White,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

// Indicador visual del estado activo
@Composable
fun StateIndicator(currentState: AppState) {
    val (color, label, description) = when (currentState) {
        AppState.LOADING -> Triple(
            Color(0xFF7C4DFF),
            "LOADING",
            "Esperando respuesta del servidor"
        )
        AppState.CONTENT -> Triple(
            Color(0xFF4CAF50),
            "CONTENT",
            "Datos obtenidos correctamente"
        )
        AppState.ERROR -> Triple(
            Color(0xFFE53935),
            "ERROR",
            "Fallo en la comunicación"
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color, shape = RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Estado: $label",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = Color(0xFF8080A0)
                )
            }
        }
    }
}