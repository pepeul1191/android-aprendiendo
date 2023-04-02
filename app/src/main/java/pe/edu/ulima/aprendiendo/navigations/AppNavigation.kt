package pe.edu.ulima.aprendiendo.navigations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.aprendiendo.activities.ui.app.ui.PokemonListScreen
import pe.edu.ulima.aprendiendo.activities.ui.app.viewmodels.PokemonListViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.ui.*
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.LoginViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.ResetPasswordViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.SignInViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavigation(
    pokemonListViewModel: PokemonListViewModel,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenApp.PokemonListScreen.route){
        composable(route = ScreenApp.PokemonListScreen.route){
            // LoadLoginScreen(navController = navController)
            PokemonListScreen(
                pokemonListViewModel
            )
        }
    }
}


sealed class ScreenApp(val route: String){
    object PokemonListScreen : Screen("pokemon_list_screen")
}

