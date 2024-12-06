package alba.oscar.digitalserv

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home2(navController: NavController) {
    @Composable
    fun BottomNavigationBar2(navController: NavController) {
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
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Inicio") },
                navigationIcon = {
                    // Icono de regresar
                    Image(
                        painter = painterResource(id = R.drawable.ic_back), // Asegúrate de tener un ícono de regresar
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.popBackStack() // Navegar hacia atrás
                            }
                    )
                },
                actions = {
                    // Icono de tres puntos (más opciones)
                    Image(
                        painter = painterResource(id = R.drawable.menu), // Asegúrate de tener este ícono (tres puntos)
                        contentDescription = "More options",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                // Aquí puedes agregar las acciones que desees para las opciones
                            }
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar2(navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pc),
                    contentDescription = "pcImage",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            navController.navigate("computer_Screen")
                        })

                Image(
                    painter = painterResource(id = R.drawable.mouse),
                    contentDescription = "mouseImage",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {

                        })

                Image(
                    painter = painterResource(id = R.drawable.keyboard),
                    contentDescription = "keyboardImage",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {

                        })
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.printer),
                    contentDescription = "printerImage",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {

                        })

                Image(
                    painter = painterResource(id = R.drawable.server),
                    contentDescription = "serverImage",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {

                        })
            }

        }
    }



}