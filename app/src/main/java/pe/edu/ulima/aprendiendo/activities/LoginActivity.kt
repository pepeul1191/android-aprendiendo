package pe.edu.ulima.aprendiendo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.LoginViewModel
import pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels.ResetPasswordViewModel
import pe.edu.ulima.aprendiendo.activities.ui.theme.AprendiendoTheme
import pe.edu.ulima.aprendiendo.navigations.LoginNavigation

class LoginActivity : ComponentActivity() {
    val loginViewModel: LoginViewModel = LoginViewModel()
    val resetPasswordViewModel: ResetPasswordViewModel = ResetPasswordViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AprendiendoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginNavigation(loginViewModel, resetPasswordViewModel)
                }
            }
        }
    }
}
