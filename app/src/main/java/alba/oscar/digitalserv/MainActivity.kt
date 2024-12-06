package alba.oscar.digitalserv

import alba.oscar.digitalserv.presentation.add.ComputerAddScreen
import alba.oscar.digitalserv.presentation.add.KeyboardAddScreen
import alba.oscar.digitalserv.presentation.add.MouseAddScreen
import alba.oscar.digitalserv.presentation.add.PrinterAddScreen
import alba.oscar.digitalserv.presentation.add.ServerAddScreen
import alba.oscar.digitalserv.presentation.delete.DeleteComputerScreen
import alba.oscar.digitalserv.presentation.delete.KeyboardDeleteScreen
import alba.oscar.digitalserv.presentation.delete.MouseDeleteScreen
import alba.oscar.digitalserv.presentation.delete.PrinterDeleteScreen
import alba.oscar.digitalserv.presentation.delete.ServerDeleteScreen
import alba.oscar.digitalserv.presentation.screens.ComputerScreen
import alba.oscar.digitalserv.presentation.screens.KeyboardScreen
import alba.oscar.digitalserv.presentation.screens.MouseScreen
import alba.oscar.digitalserv.presentation.screens.PrinterScreen
import alba.oscar.digitalserv.presentation.screens.ServerScreen
import alba.oscar.digitalserv.presentation.state.ComputerStateScreen
import alba.oscar.digitalserv.presentation.state.KeyboardStateScreen
import alba.oscar.digitalserv.presentation.state.MouseStateScreen
import alba.oscar.digitalserv.presentation.state.PrinterStateScreen
import alba.oscar.digitalserv.presentation.state.ServerStateScreen
import alba.oscar.digitalserv.presentation.update.ComputerUpdateScreen
import alba.oscar.digitalserv.presentation.update.KeyboardUpdateScreen
import alba.oscar.digitalserv.presentation.update.MouseUpdateScreen
import alba.oscar.digitalserv.presentation.update.PrinterUpdateScreen
import alba.oscar.digitalserv.presentation.update.ServerUpdateScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class
MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login_Screen", builder = {
                composable("login_Screen",) {
                    LoginScreen(navController)
                }

                composable("register_Screen",) {
                    RegisterScreen(navController)
                }

                composable("home_Screen",) {
                    HomeScreen(navController)
                }

                composable("home2",) {
                    Home2(navController)
                }

                composable("computer_Screen",) {
                    ComputerScreen(navController)
                }

                composable("computer_AddScreen",) {
                    ComputerAddScreen(navController)
                }

                composable("computerState_Screen",) {
                    ComputerStateScreen(navController)
                }

                composable("mouse_Screen",) {
                    MouseScreen(navController)
                }

                composable("mouse_AddScreen",) {
                    MouseAddScreen(navController)
                }

                composable("mouseState_Screen",) {
                    MouseStateScreen(navController)
                }

                composable("keyboard_Screen",) {
                    KeyboardScreen(navController)
                }

                composable("keyboard_AddScreen",) {
                    KeyboardAddScreen(navController)
                }

                composable("keyboardState_Screen",) {
                    KeyboardStateScreen(navController)
                }

                composable("printer_Screen",) {
                    PrinterScreen(navController)
                }

                composable("printer_AddScreen",) {
                    PrinterAddScreen(navController)
                }

                composable("printerState_Screen",){
                    PrinterStateScreen(navController)
                }

                composable("server_Screen",){
                    ServerScreen(navController)
                }

                composable("server_AddScreen",){
                    ServerAddScreen(navController)
                }

                composable("serverState_Screen",){
                    ServerStateScreen(navController)
                }
                composable("computerUpdate_Screen",){
                    ComputerUpdateScreen(navController)
                }
                composable("computerDelete_Screen",){
                    DeleteComputerScreen(navController)
                }
                composable("MouseUpdate_Screen",){
                    MouseUpdateScreen(navController)
                }
                composable("MouseDelete_Screen",){
                    MouseDeleteScreen(navController)
                }
                composable("KeyboardUpdate_Screen",){
                    KeyboardUpdateScreen(navController)
                }
                composable("KeyboardDelete_Screen",){
                    KeyboardDeleteScreen(navController)
                }
                composable("PrinterUpdate_Screen",){
                    PrinterUpdateScreen(navController)
                }
                composable("PrinterDelete_Screen",){
                    PrinterDeleteScreen(navController)
                }
                composable("ServerUpdate_Screen",){
                    ServerUpdateScreen(navController)
                }
                composable("ServerDelete_Screen",){
                    ServerDeleteScreen(navController)
                }

            })
        }
    }
}
