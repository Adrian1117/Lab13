package com.example.lab13.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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

// Enum para manejar los modos de animación
enum class AnimationMode { TWEEN, SPRING }

@Composable
fun Exercise2Screen() {

    // Estado: controla si el color es azul o verde
    var isFirstColor by remember { mutableStateOf(true) }

    // Estado: modo de animación seleccionado
    var animMode by remember { mutableStateOf(AnimationMode.TWEEN) }

    // animateColorAsState con tween (duración fija y suave)
    val animatedColorTween by animateColorAsState(
        targetValue = if (isFirstColor) Color(0xFF1565C0) else Color(0xFF2E7D32),
        animationSpec = tween(durationMillis = 900),
        label = "colorTween"
    )

    // animateColorAsState con spring (efecto de rebote físico)
    val animatedColorSpring by animateColorAsState(
        targetValue = if (isFirstColor) Color(0xFF1565C0) else Color(0xFF2E7D32),
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "colorSpring"
    )

    // Color activo según el modo elegido
    val activeColor = if (animMode == AnimationMode.TWEEN)
        animatedColorTween else animatedColorSpring

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
            text = "Ejercicio 2",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "animateColorAsState",
            fontSize = 16.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // Cuadro que cambia de color animado
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    color = activeColor,
                    shape = RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (isFirstColor) "🔵" else "🟢",
                    fontSize = 48.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isFirstColor) "Azul" else "Verde",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para cambiar el color
        Button(
            onClick = { isFirstColor = !isFirstColor },
            colors = ButtonDefaults.buttonColors(
                containerColor = activeColor
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (isFirstColor) "Cambiar a Verde 🟢" else "Cambiar a Azul 🔵",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Selector de tipo de animación
        Text(
            text = "Tipo de animación:",
            fontSize = 14.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón TWEEN
            Button(
                onClick = { animMode = AnimationMode.TWEEN },
                modifier = Modifier.weight(1f).height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (animMode == AnimationMode.TWEEN)
                        Color(0xFF7C4DFF) else Color(0xFF2A2A3E)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Tween",
                    color = Color.White,
                    fontWeight = if (animMode == AnimationMode.TWEEN)
                        FontWeight.Bold else FontWeight.Normal
                )
            }

            // Botón SPRING
            Button(
                onClick = { animMode = AnimationMode.SPRING },
                modifier = Modifier.weight(1f).height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (animMode == AnimationMode.SPRING)
                        Color(0xFF7C4DFF) else Color(0xFF2A2A3E)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Spring",
                    color = Color.White,
                    fontWeight = if (animMode == AnimationMode.SPRING)
                        FontWeight.Bold else FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta informativa
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Modo activo: ${if (animMode == AnimationMode.TWEEN) "Tween" else "Spring"}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C4DFF)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (animMode == AnimationMode.TWEEN)
                        "⏱ tween(900ms): transición de duración fija y lineal. Predecible y controlada."
                    else
                        "🌊 spring(LowStiffness): simula física real con rebote. Más orgánica y natural.",
                    fontSize = 12.sp,
                    color = Color(0xFF8080A0),
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}