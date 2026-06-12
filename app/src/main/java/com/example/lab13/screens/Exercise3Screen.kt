package com.example.lab13.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
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

@Composable
fun Exercise3Screen() {

    // Estado: controla si el cuadro está expandido
    var expanded by remember { mutableStateOf(false) }

    // Estado: controla si está en posición alternativa
    var moved by remember { mutableStateOf(false) }

    // Animación del TAMAÑO con spring (rebote físico)
    val animatedSize by animateDpAsState(
        targetValue = if (expanded) 220.dp else 110.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "sizeAnimation"
    )

    // Animación de POSICIÓN X con tween (suave y controlado)
    val animatedOffsetX by animateDpAsState(
        targetValue = if (moved) 90.dp else 0.dp,
        animationSpec = tween(durationMillis = 700),
        label = "offsetXAnimation"
    )

    // Animación de POSICIÓN Y con tween
    val animatedOffsetY by animateDpAsState(
        targetValue = if (moved) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 700),
        label = "offsetYAnimation"
    )

    // Color que cambia según el estado
    val boxColor = when {
        expanded && moved -> Color(0xFFE91E63)   // Rosa: expandido Y movido
        expanded -> Color(0xFF7C4DFF)             // Morado: solo expandido
        moved -> Color(0xFFFF9800)                // Naranja: solo movido
        else -> Color(0xFF00BCD4)                 // Cyan: estado inicial
    }

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
            text = "Ejercicio 3",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "animateDpAsState + Modifier.offset",
            fontSize = 14.sp,
            color = Color(0xFFB0B0C0),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // Área de animación con fondo delimitado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    color = Color(0xFF2A2A3E),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.TopStart
        ) {
            // El orden importa: primero offset, luego size
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = animatedOffsetX, y = animatedOffsetY)
                    .size(animatedSize)
                    .background(
                        color = boxColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = when {
                            expanded && moved -> "🎯"
                            expanded -> "⬆️"
                            moved -> "➡️"
                            else -> "📦"
                        },
                        fontSize = if (expanded) 40.sp else 28.sp
                    )
                    if (expanded) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Grande!",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Info del estado actual
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A3E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Tamaño actual
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Tamaño",
                        fontSize = 11.sp,
                        color = Color(0xFF8080A0)
                    )
                    Text(
                        text = if (expanded) "220dp" else "110dp",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7C4DFF)
                    )
                }

                // Divider vertical
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(40.dp)
                        .background(Color(0xFF3A3A4E))
                )

                // Offset X actual
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Offset X",
                        fontSize = 11.sp,
                        color = Color(0xFF8080A0)
                    )
                    Text(
                        text = if (moved) "90dp" else "0dp",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9800)
                    )
                }

                // Divider vertical
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(40.dp)
                        .background(Color(0xFF3A3A4E))
                )

                // Offset Y actual
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Offset Y",
                        fontSize = 11.sp,
                        color = Color(0xFF8080A0)
                    )
                    Text(
                        text = if (moved) "40dp" else "0dp",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00BCD4)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón expandir / reducir
        Button(
            onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C4DFF)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = if (expanded) "🔽  Reducir tamaño" else "🔼  Expandir tamaño",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón mover / regresar
        Button(
            onClick = { moved = !moved },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800)
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = if (moved) "⬅️  Regresar posición" else "➡️  Mover posición",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Nota sobre el orden de modificadores
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2A1A)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "💡 Orden de Modifier importa:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF81C784)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ".offset(x, y)  →  .size()   ✅ correcto\n.size()  →  .offset(x, y)   ⚠️ el origen cambia",
                    fontSize = 11.sp,
                    color = Color(0xFF8080A0),
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}