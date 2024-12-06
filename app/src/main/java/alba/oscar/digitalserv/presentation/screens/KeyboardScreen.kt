package alba.oscar.digitalserv.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardScreen(navController: NavController) {
    val db = Firebase.firestore
    val Keyboards = remember { mutableStateOf<List<Map<String, String>>>(emptyList()) }

    // Carga de datos desde Firebase Firestore
    LaunchedEffect(Unit) {
        db.collection("Keyboards")
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.map { document ->
                    document.data.mapValues { it.value.toString() } // Convertir a mapa de cadenas
                }
                Keyboards.value = dataList
            }
            .addOnFailureListener {
                // Manejar el error si ocurre
                Log.e("FirebaseError", "Error al obtener datos: ${it.message}")
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Teclados",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home_Screen") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF001F54) // Barra de color Azul marino
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Respeta el espacio ocupado por el topBar
        ) {
            // Tabla con registros obtenidos de Firebase
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter) // Alineación superior
                    .border(BorderStroke(2.dp, Color.Black)) // Borde de la tabla
            ) {
                // Cabecera de la tabla
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF001F54)) // Fondo azul para la cabecera
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Serial", "Nombre", "Marca", "Modelo", "Periodicidad").forEach { header ->
                        Text(
                            text = header,
                            color = Color.White,
                            modifier = Modifier.weight(1f), // Distribución equitativa
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                // Filas de datos desde Firestore
                if (Keyboards.value.isNotEmpty()) {
                    Keyboards.value.forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(
                                row["serialNumber"] ?: "",
                                row["name"] ?: "",
                                row["brand"] ?: "",
                                row["model"] ?: "",
                                row["maintenancePeriod"] ?: ""
                            ).forEachIndexed { index, cell ->
                                // Hacer clic en la columna "Periodicidad"
                                if (index == 4) { // Índice de la columna "Periodicidad"
                                    Text(
                                        text = cell,
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                navController.navigate("keyboardState_Screen")
                                            },
                                        style = MaterialTheme.typography.bodySmall
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
                    // Mostrar mensaje si no hay datos
                    Text(
                        text = "Cargando datos...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            // Botón alineado a la parte inferior derecha
            Button(
                onClick = { navController.navigate("keyboard_AddScreen") },
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
                onClick = { navController.navigate("KeyboardDelete_Screen") },
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
                onClick = { navController.navigate("KeyboardUpdate_Screen") },
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
