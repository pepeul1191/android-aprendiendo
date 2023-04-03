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
import pe.edu.ulima.aprendiendo.models.beans.GenerationOption
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import pe.edu.ulima.aprendiendo.services.PokemonService
import retrofit2.Response
import kotlin.concurrent.thread

class PokemonListViewModel : ViewModel() {
    private val _pokemonList = MutableLiveData<List<PokemonGeneration>>()
    var pokemonList: LiveData<List<PokemonGeneration>> = _pokemonList

    private val _generationList = MutableLiveData<List<GenerationOption>>()
    var generationList: LiveData<List<GenerationOption>> = _generationList

    fun fetch(pokemonName: String, context: Activity) {
        // generate generations id string for query parameter
        var generationIds: String = "";
        _generationList.value?.forEach { item ->
            if(item.selected){
                generationIds += "${item.id}||"
            }
        }
        if(generationIds.length > 0){
            generationIds = generationIds.substring(0, generationIds.length - 2)
        }
        // http resquest
        val apiService = BackendClient.buildService(PokemonService::class.java)
        thread  {
            try {
                val response = apiService.fetch(pokemonName, "").execute()
                if (response.isSuccessful) {
                    // Log.d("PokemonList", response.body().toString())
                    // update pokemons list
                    _pokemonList.postValue(response.body())
                    // update generations list
                    response.body()?.let { doGenerationList(it, generationIds) }
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

    fun doGenerationList(pokemonList: PokemonListResponse, generationIds: String){
        val tmpListPokemonGenerationOption = mutableListOf<GenerationOption>()
        if(generationIds.length == 0){
            if (tmpListPokemonGenerationOption != null) {
                for (pokemon in pokemonList) {
                    if (genertionNameInList(
                            pokemon.generationName,
                            tmpListPokemonGenerationOption
                        ) == false
                    ) {
                        tmpListPokemonGenerationOption.add(
                            GenerationOption(
                                pokemon.generationId,
                                pokemon.generationName,
                                false
                            )
                        )
                    }
                }
                _generationList.postValue(tmpListPokemonGenerationOption.toList())
            }
        }
    }

    private fun genertionNameInList(generationName: String, tmpGenerationList: MutableList<GenerationOption>): Boolean{
        var exist: Boolean = false
        tmpGenerationList.forEach{
            if(it.name == generationName) {
                exist = true
                return@forEach
            }
        }
        return exist
    }

    fun generationChecked(generation: GenerationOption, checked: Boolean){
        generation.selected = checked
        println(generation.toString())
        _generationList.value = generationList.value
    }
}