package pe.edu.ulima.aprendiendo.viewmodels

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.edu.ulima.aprendiendo.configs.BackendClient
import pe.edu.ulima.aprendiendo.models.beans.PokemonGeneration
import pe.edu.ulima.aprendiendo.models.beans.GenerationOption
import pe.edu.ulima.aprendiendo.services.PokemonService
import kotlin.concurrent.thread

class PokemonListViewModel: ViewModel() {
    // list of pokemons
    private val _pokemonList = MutableLiveData<List<PokemonGeneration>>()
    val items: LiveData<List<PokemonGeneration>> = _pokemonList
    // list of pokemons types
    private var _generationList = MutableLiveData<List<GenerationOption>>()
    val generationList: LiveData<List<GenerationOption>> = _generationList

    fun fetch(pokemonName: String, activity: Activity) {
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
        // update model with retrofit
        viewModelScope.launch {
            val apiService = BackendClient.buildService(PokemonService::class.java)
            thread{
                try {
                    val response = apiService.fetch(pokemonName, generationIds).execute()
                    if (response.isSuccessful) {
                        // Log.d("PokemonListViewModel", response.body().toString())
                        _pokemonList.postValue(response.body())
                        val dataFechted = response.body()
                        val tmpListPokemonGenerationOption = mutableListOf<GenerationOption>()
                        if(generationIds.length == 0){
                            if (dataFechted != null) {
                                for (pokemon in dataFechted) {
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
                        // var newPokemonTypeList = generatePokemonTypeList()
                    } else {
                        // Handle error
                        activity.runOnUiThread{
                            Toast.makeText(
                                activity,
                                "Error: No se pudo mostrar la lista de pokemones",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    activity.runOnUiThread{
                        Toast.makeText(
                            activity,
                            "Error HTTP: No se pudo traer la lista de pokemones",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

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

    fun updateGenerations(selectedItems: List<String>, unSelectedItems: List<String>){
        // select
        selectedItems.forEach{
            val generationName = it
            _generationList.value?.forEach { item ->
                if (item.name == generationName) {
                    item.selected = true
                }
            }
        }
        // unselect
        unSelectedItems.forEach{
            val generationName = it
            _generationList.value?.forEach { item ->
                if (item.name == generationName) {
                    item.selected = false
                }
            }
        }
    }
}