package alba.oscar.digitalserv

import android.annotation.SuppressLint
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        containerColor = Color.White,
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Inicio",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
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
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val borderWidth = 4.dp
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.pc),
                        contentDescription = "Computadoras",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .clickable {
                                navController.navigate("computer_Screen")
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Computadoras",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "Mouse",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .clickable {
                                navController.navigate("mouse_Screen")
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ratones",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.keyboard),
                        contentDescription = "Keyboard",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .clickable {
                                navController.navigate("keyboard_Screen")
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Teclados",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                val borderWidth = 4.dp
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.printer),
                        contentDescription = "Printer",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .clickable {
                                navController.navigate("printer_Screen")
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Impresoras",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.server),
                        contentDescription = "Server",
                        modifier = Modifier
                            .size(125.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(borderWidth, Color.Black))
                            .clickable {
                                navController.navigate("server_Screen")
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Servidores",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
            }


        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary, // Color azul para el fondo
            contentColor = MaterialTheme.colorScheme.onPrimary // Color de los íconos y texto
        ) {
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = "Home") },
                selected = true, // Cambia dinámicamente si esta es la pantalla actual
                onClick = {
                    navController.navigate("home") // Navega a la pantalla Home
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary, // Ícono seleccionado
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary, // Texto seleccionado
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f), // Ícono no seleccionado
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)  // Texto no seleccionado
                )
            )

            //Barra de navegación inferior
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = "Search") },
                selected = false, // Cambia dinámicamente si esta es la pantalla actual
                onClick = {
                    navController.navigate("search") // Navega a la pantalla Search
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            )
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = "Profile") },
                selected = false, // Cambia dinámicamente si esta es la pantalla actual
                onClick = {
                    navController.navigate("profile") // Navega a la pantalla Profile
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                )
            )
        }
    }
}