package pe.edu.ulima.aprendiendo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pe.edu.ulima.aprendiendo.activities.ui.app.viewmodels.PokemonListViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.LoginViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.ResetPasswordViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.SignInViewModel
import pe.edu.ulima.aprendiendo.activities.ui.theme.AprendiendoTheme
import pe.edu.ulima.aprendiendo.navigations.AppNavigation
import pe.edu.ulima.aprendiendo.navigations.LoginNavigation

class AppActivity: ComponentActivity() {
    val pokemonListViewModel: PokemonListViewModel = PokemonListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AprendiendoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(
                        pokemonListViewModel,
                    )
                }
            }
        }
    }
}