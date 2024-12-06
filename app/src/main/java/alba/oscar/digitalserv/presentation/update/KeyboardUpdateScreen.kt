package alba.oscar.digitalserv.presentation.update

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.initialize
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import org.w3c.dom.Text

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardUpdateScreen(navController: NavController) {
    val context = LocalContext.current
    val db = Firebase.firestore // Instancia de Firestore

    // Variables de estado
    val serialNumber = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val model = remember { mutableStateOf("") }
    val maintenancePeriod = remember { mutableStateOf("") }
    val status = remember { mutableStateOf("") }
    val isEditing = remember { mutableStateOf(false) } // Estado para indicar si se encontró el teclado
    val documentId = remember { mutableStateOf("") } // ID del documento de Firestore

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Actualizar Teclado", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("keyboard_Screen") }) {
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
    ) {}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isEditing.value) {
            Text(text = "Buscar teclado por número serial", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = serialNumber.value,
                onValueChange = { serialNumber.value = it },
                label = { Text("Número serial") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (serialNumber.value.isNotBlank()) {
                        db.collection("Keyboards") // Cambiado a la colección 'mice'
                            .whereEqualTo("serialNumber", serialNumber.value)
                            .get()
                            .addOnSuccessListener { documents ->
                                if (documents.isEmpty) {
                                    Toast.makeText(context, "No se encontró el Teclado", Toast.LENGTH_SHORT).show()
                                } else {
                                    val document = documents.documents[0]
                                    documentId.value = document.id
                                    name.value = document.getString("name") ?: ""
                                    brand.value = document.getString("brand") ?: ""
                                    model.value = document.getString("model") ?: ""
                                    maintenancePeriod.value = document.getString("maintenancePeriod") ?: ""
                                    status.value = document.getString("status") ?: ""
                                    isEditing.value = true
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error al buscar: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "Por favor, ingresa un número serial", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF001F54),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Buscar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            Text(text = "Actualizar datos del teclado", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = brand.value,
                onValueChange = { brand.value = it },
                label = { Text("Marca") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = model.value,
                onValueChange = { model.value = it },
                label = { Text("Modelo") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = maintenancePeriod.value,
                onValueChange = { maintenancePeriod.value = it },
                label = { Text("Periodicidad Mantenimiento") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = status.value,
                onValueChange = { status.value = it },
                label = { Text("Estado del equipo") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.value.isNotBlank() && brand.value.isNotBlank()) {
                        val updatedData = mapOf(
                            "name" to name.value,
                            "brand" to brand.value,
                            "model" to model.value,
                            "maintenancePeriod" to maintenancePeriod.value,
                            "status" to status.value
                        )
                        db.collection("Keyboards") // Cambiado a la colección 'mice'
                            .document(documentId.value)
                            .update(updatedData)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                                navController.navigate("Keyboard_Screen")
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error al actualizar: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF001F54),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Actualizar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
