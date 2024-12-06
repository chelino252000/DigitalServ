package alba.oscar.digitalserv.presentation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComputerScreen(navController: NavController) {
    val db = Firebase.firestore
    val computers = remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    val context = LocalContext.current

    // Consultar datos desde Firestore
    LaunchedEffect(Unit) {
        db.collection("computers")
            .get()
            .addOnSuccessListener { result ->
                val fetchedComputers = result.map { it.data }
                computers.value = fetchedComputers
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error al cargar datos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Computadoras", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home_Screen") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF001F54)
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tabla dinámica
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
                    .border(BorderStroke(2.dp, Color.Black))
            ) {
                // Cabecera de la tabla
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF001F54))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Serial", "Nombre", "Marca", "Modelo", "Fecha Adqui.", "Periodicidad").forEach { header ->
                        Text(
                            text = header,
                            color = Color.White,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                // Filas de datos dinámicos
                if (computers.value.isNotEmpty()) {
                    computers.value.forEach { computer ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            val cells = listOf(
                                computer["serialNumber"]?.toString() ?: "",
                                computer["name"]?.toString() ?: "",
                                computer["brand"]?.toString() ?: "",
                                computer["model"]?.toString() ?: "",
                                computer["acquisitionDate"]?.toString() ?: "",
                                computer["maintenancePeriod"]?.toString() ?: ""
                            )

                            cells.forEachIndexed { index, cell ->
                                if (index == 5) { // Columna de "Periodicidad"
                                    Text(
                                        text = cell,
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("computerState_Screen")
                                            },
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Blue
                                    )
                                } else {
                                    Text(
                                        text = cell,
                                        modifier = Modifier.weight(1f),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Mensaje si no hay datos
                    Text(
                        text = "No hay computadoras registradas.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Botón de añadir computadora
            Button(
                onClick = { navController.navigate("computer_AddScreen") },
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF001F54),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir",
                    modifier = Modifier.size(38.dp)
                )
            }
            // Botón de eliminar computadora
            Button(
                onClick = { navController.navigate("computerDelete_Screen") },
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF001F54),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(38.dp)
                )
            }
            // Botón de actualizar computadora
            Button(
                onClick = { navController.navigate("computerUpdate_Screen") },
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF001F54),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Actualizar",
                    modifier = Modifier.size(38.dp)
                )
            }

        }
    }
}
