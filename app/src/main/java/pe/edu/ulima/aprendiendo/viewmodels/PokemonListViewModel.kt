package pe.edu.ulima.aprendiendo.viewmodels

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.edu.ulima.aprendiendo.activities.PokemonListActivity
import pe.edu.ulima.aprendiendo.configs.BackendClient
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import pe.edu.ulima.aprendiendo.models.views.QuoteModel
import pe.edu.ulima.aprendiendo.providers.PokemonListProvider
import pe.edu.ulima.aprendiendo.providers.QuoteProvider
import pe.edu.ulima.aprendiendo.services.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class PokemonListViewModel: ViewModel() {
    private val _pokemonList = MutableLiveData<List<PokemonGeneration>>()
    val items: LiveData<List<PokemonGeneration>> = _pokemonList

    fun fetch(pokemonName: String, activity: Activity) {
        viewModelScope.launch {
            val apiService = BackendClient.buildService(PokemonService::class.java)
            thread{
                val response = apiService.fetch(pokemonName).execute()
                if (response.isSuccessful) {
                    // Log.d("PokemonListViewModel", response.body().toString())
                    _pokemonList.postValue(response.body())
                } else {
                    // Handle error
                    Toast.makeText(
                        activity,
                        "Error: No se pudo traer la lista de pokemones",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}