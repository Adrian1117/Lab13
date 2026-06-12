package com.example.lab13.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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

@Composable
fun Exercise1Screen() {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C2E))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Ejercicio 1",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "AnimatedVisibility",
            fontSize = 16.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        Button(
            onClick = { visible = !visible },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (visible) Color(0xFFE57373) else Color(0xFF7C4DFF)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (visible) "🙈  Ocultar cuadro" else "👁  Mostrar cuadro",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 600)) +
                    slideInVertically(
                        animationSpec = tween(durationMillis = 600),
                        initialOffsetY = { -it }
                    ),
            exit = fadeOut(animationSpec = tween(durationMillis = 400)) +
                    slideOutVertically(
                        animationSpec = tween(durationMillis = 400),
                        targetOffsetY = { it }
                    )
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(
                        color = Color(0xFF7C4DFF),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "✨", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡Aquí estoy!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Estado actual:",
                    fontSize = 13.sp,
                    color = Color(0xFFB0B0C0)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (visible) "🟢 Visible" else "🔴 Oculto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (visible) Color(0xFF81C784) else Color(0xFFE57373)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Entrada: fadeIn + slideInVertically\nSalida: fadeOut + slideOutVertically",
                    fontSize = 12.sp,
                    color = Color(0xFF8080A0),
                    lineHeight = 18.sp
                )
            }
        }
    }
}