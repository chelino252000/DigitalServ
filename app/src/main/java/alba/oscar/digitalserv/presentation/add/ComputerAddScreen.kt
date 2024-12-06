package alba.oscar.digitalserv.presentation.add

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
fun ComputerAddScreen(navController: NavController) {
    val context = LocalContext.current
    val db = Firebase.firestore // Instancia de Firestore

    // Variables de estado correctamente declaradas como String
    val serialNumber = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val model = remember { mutableStateOf("") }
    val maintenancePeriod = remember { mutableStateOf("") }
    val status = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Añadir Computadora", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("computer_Screen") }) {
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
        Text(text = "Ingresa los datos de la PC", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = serialNumber.value,
            onValueChange = { serialNumber.value = it },
            label = { Text(text = "Número serial") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = brand.value,
            onValueChange = { brand.value = it },
            label = { Text(text = "Marca") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = model.value,
            onValueChange = { model.value = it },
            label = { Text(text = "Modelo") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = maintenancePeriod.value,
            onValueChange = { maintenancePeriod.value = it },
            label = { Text(text = "Periodicidad Mantenimiento") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = status.value,
            onValueChange = { status.value = it },
            label = { Text(text = "Estado del equipo") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (serialNumber.value.isNotBlank() && name.value.isNotBlank() && brand.value.isNotBlank()) {
                    val computer = hashMapOf(
                        "serialNumber" to serialNumber.value,
                        "name" to name.value,
                        "brand" to brand.value,
                        "model" to model.value,
                        "maintenancePeriod" to maintenancePeriod.value,
                        "status" to status.value
                    )

                    db.collection("computers")
                        .add(computer)
                        .addOnSuccessListener {
                            Toast.makeText(context, "Computadora registrada con éxito", Toast.LENGTH_SHORT).show()
                            navController.navigate("computer_Screen")
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
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
            Text(text = "Añadir", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}