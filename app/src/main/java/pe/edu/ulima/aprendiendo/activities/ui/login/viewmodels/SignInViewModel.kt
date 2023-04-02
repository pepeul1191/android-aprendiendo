package pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.aprendiendo.configs.BackendClient
import pe.edu.ulima.aprendiendo.models.requests.UserCreateRequest
import pe.edu.ulima.aprendiendo.models.requests.UserValidateRequest
import pe.edu.ulima.aprendiendo.services.UserService
import java.util.regex.Pattern
import kotlin.concurrent.thread

class SignInViewModel: ViewModel() {
    private val _user = MutableLiveData<String>("")
    var user: LiveData<String> = _user
    fun updateUser(it: String) {
        _user.value = it
    }

    private val _email = MutableLiveData<String>("")
    var email: LiveData<String> = _email
    fun updateEmail(it: String) {
        _email.value = it
    }

    private val _password = MutableLiveData<String>("")
    var password: LiveData<String> = _password
    fun updatePassword(it: String) {
        _password.value = it
    }

    private val _password2 = MutableLiveData<String>("")
    var password2: LiveData<String> = _password2
    fun updatePassword2(it: String) {
        _password2.value = it
    }

    private fun modelNotEmpty(): Boolean{
        val user = _user.value!!
        val email = _email.value!!
        val password = _password.value!!
        val password2 = _password2.value!!
        if (user != "" && password != "" && email != "" && password2 != ""){
            return true
        }else{
            return false
        }
    }

    private fun equalPassword(): Boolean{
        val password = _password.value!!
        val password2 = _password2.value!!
        if (password == password2){
            return true
        }else{
            return false
        }
    }

    private fun validEmail(): Boolean{
        val email = _email.value!!
        val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun showMessage(message: String, context:Activity) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun signIn(context: Activity){
        // validations
        if(this.modelNotEmpty()){
            if(this.validEmail()){
                if(this.equalPassword()){
                    // form data model
                    val user = _user.value!!
                    val password = _password.value!!
                    val email = _email.value!!
                    // http resquest
                    val apiService = BackendClient.buildService(UserService::class.java)
                    thread{
                        try {
                            val newUser: UserCreateRequest = UserCreateRequest(user, password, email)
                            println("1 ++++++++++++++++++++++++++++")
                            println(newUser.toString())
                            println("2 ++++++++++++++++++++++++++++")
                            val response = apiService.create(newUser).execute()
                            if (response.isSuccessful) {
                                Log.d("User Validate", response.body().toString())
                            } else {
                                // Handle error
                                showMessage(response.errorBody()?.string()!!, context)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            showMessage("Error HTTP: No se pudo conectar con el servicio", context)
                        }
                    }
                }else{
                    showMessage("Las contraseñas deben de ser iguales.", context)
                }
            }else{
                showMessage("Debe de ingresar un correo válido.", context)
            }
        }else{
            showMessage("Debe de llenar todo el formulario.", context)
        }
    }


}