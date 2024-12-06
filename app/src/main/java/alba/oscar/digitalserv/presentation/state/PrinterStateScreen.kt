package alba.oscar.digitalserv.presentation.state

import alba.oscar.digitalserv.R
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrinterStateScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        // TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Estado de la Impresora",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("printer_Screen") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF001F54) // Barra de color Azul marino
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val borderWidth = 4.dp

            // Estados locales para controlar el color de fondo de cada imagen
            var functionalColor by remember { mutableStateOf(Color.White) }
            var maintenanceColor by remember { mutableStateOf(Color.White) }
            var disconnectedColor by remember { mutableStateOf(Color.White) }
            var notFunctionalColor by remember { mutableStateOf(Color.White) }

            // Primera fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.monitor),
                        contentDescription = "Funcional",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .background(functionalColor) // Fondo dinámico
                            .clickable {
                                functionalColor = if (functionalColor == Color.White) Color.Green else Color.White
                            } // Alterna el color
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Funcional",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.mantenimiento),
                        contentDescription = "Mantenimiento",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .background(maintenanceColor) // Fondo dinámico
                            .clickable {
                                maintenanceColor = if (maintenanceColor == Color.White) Color.Yellow else Color.White
                            } // Alterna el color
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mantenimiento",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.obsolete_11651137),
                        contentDescription = "Desconectado",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .background(disconnectedColor) // Fondo dinámico
                            .clickable {
                                disconnectedColor = if (disconnectedColor == Color.White) Color(0xFFFFA500) else Color.White
                            } // Alterna el color
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Desconectado",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Segunda fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.muerto),
                        contentDescription = "No funcional",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .background(notFunctionalColor) // Fondo dinámico
                            .clickable {
                                notFunctionalColor = if (notFunctionalColor == Color.White) Color.Red else Color.White
                            } // Alterna el color
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "No funcional",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
