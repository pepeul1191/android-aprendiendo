package pe.edu.ulima.aprendiendo.activities.ui.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel: ViewModel(){
    private val _error = MutableLiveData<Boolean>(false)
    var error: LiveData<Boolean> = _error
    fun updateError(error: Boolean){
        _error.value = error
    }
}