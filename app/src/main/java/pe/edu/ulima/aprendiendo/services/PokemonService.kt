package pe.edu.ulima.aprendiendo.services

import pe.edu.ulima.aprendiendo.models.responses.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("/pokemon/list")
    fun fetch(@Query("name") name: String): Call<PokemonListResponse>
}