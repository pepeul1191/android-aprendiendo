package pe.edu.ulima.aprendiendo.activities.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    private val _user = MutableLiveData<String>("")
    var user: LiveData<String> = _user

    private val _password = MutableLiveData<String>("")
    var password: LiveData<String> = _password

    fun updateUser(it: String){
        _user.value = it
    }

    fun updatePassword(it: String){
        _password.value = it
    }

    fun login(){
        println("usuario: ${_user.value}, contraseña: ${_password.value}")
    }
}