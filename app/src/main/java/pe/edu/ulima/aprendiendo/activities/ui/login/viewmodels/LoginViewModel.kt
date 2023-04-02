package pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.aprendiendo.activities.AppActivity
import pe.edu.ulima.aprendiendo.configs.BackendClient
import pe.edu.ulima.aprendiendo.models.requests.UserValidateRequest
import pe.edu.ulima.aprendiendo.services.UserService
import kotlin.concurrent.thread

class LoginViewModel: ViewModel(){
    private val _user = MutableLiveData<String>("")
    var user: LiveData<String> = _user
    fun updateUser(it: String){
        _user.postValue(it)
    }

    private val _password = MutableLiveData<String>("")
    var password: LiveData<String> = _password
    fun updatePassword(it: String){
        _password.value = it
    }

    private val _toasMessage = MutableLiveData<String>("")
    var toasMessage: LiveData<String> = _toasMessage
    fun updateToasMessage(it: String){
        _toasMessage.value = it
    }

    private fun modelNotEmpty(): Boolean{
        val user = _user.value!!
        val password = _password.value!!
        if (user == "" || password == ""){
            return true
        }else{
            return false
        }
    }

    fun login(context: Activity ){
        // println("usuario: ${_user.value}, contraseña: ${_password.value}")
        // validate model
        if (this.modelNotEmpty()){
            showMessage("Debe de ingresar el usuario y la contarseña", context)
        }else{
            // http resquest
            val apiService = BackendClient.buildService(UserService::class.java)
            // form data model
            val user = _user.value!!
            val password = _password.value!!
            thread{
                try {
                    val user: UserValidateRequest = UserValidateRequest(user, password)
                    val response = apiService.validate(user).execute()
                    if (response.isSuccessful) {
                        Log.d("User Validate", response.body().toString())
                        context.startActivity(Intent(context, AppActivity::class.java))
                    } else {
                        // Handle error
                        showMessage(response.errorBody()?.string()!!, context)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    showMessage("Error HTTP: No se pudo conectar con el servicio", context)
                }
            }
        }
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
}