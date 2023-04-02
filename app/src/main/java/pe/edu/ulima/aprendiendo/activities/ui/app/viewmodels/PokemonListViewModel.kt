package pe.edu.ulima.aprendiendo.activities.ui.app.viewmodels

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
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import pe.edu.ulima.aprendiendo.services.PokemonService
import kotlin.concurrent.thread

class PokemonListViewModel : ViewModel() {
    private val _pokemonList = MutableLiveData<List<PokemonGeneration>>()
    var pokemonList: LiveData<List<PokemonGeneration>> = _pokemonList

    fun fetch(pokemonName: String, context: Activity) {
        // http resquest
        val apiService = BackendClient.buildService(PokemonService::class.java)
        thread  {
            try {
                val response = apiService.fetch(pokemonName, "").execute()
                if (response.isSuccessful) {
                    Log.d("PokemonList", response.body().toString())
                    _pokemonList.postValue(response.body())
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