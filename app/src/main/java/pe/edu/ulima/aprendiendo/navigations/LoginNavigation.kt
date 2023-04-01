package pe.edu.ulima.aprendiendo.navigations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.aprendiendo.activities.ui.login.ui.LoginScreen
import pe.edu.ulima.aprendiendo.activities.ui.login.ui.LoginViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.ui.ResetPasswordScreen
import pe.edu.ulima.aprendiendo.activities.ui.login.ui.SignInScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginNavigation(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
           // LoadLoginScreen(navController = navController)
            LoginScreen(
                loginViewModel,
                goToSignIn = {
                    navController.navigate(Screen.SignInScreen.route)
                },
                goToResetPassword = {
                    navController.navigate(Screen.ResetPasswordScreen.route)
                }
            )
        }
        composable(route = Screen.SignInScreen.route){
            // LoadLoginScreen(navController = navController)
            SignInScreen(
                goToLogin = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                goToResetPassword = {
                    navController.navigate(Screen.ResetPasswordScreen.route)
                }
            )
        }
        composable(route = Screen.ResetPasswordScreen.route){
            // LoadLoginScreen(navController = navController)
            ResetPasswordScreen(
                goToLogin = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                goToSignIn = {
                    navController.navigate(Screen.SignInScreen.route)
                },
            )
        }
    }
}


sealed class Screen(val route: String){
    object LoginScreen : Screen("login_screen")
    object SignInScreen : Screen("sign_screen")
    object ResetPasswordScreen : Screen("reset_password")
}

