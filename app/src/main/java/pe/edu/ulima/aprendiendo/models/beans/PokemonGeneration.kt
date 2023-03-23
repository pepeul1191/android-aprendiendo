package pe.edu.ulima.aprendiendo.models.beans

import com.google.gson.annotations.SerializedName

data class PokemonGeneration(
    val id: Int,
    val name: String,
    val number: Int,
    val height: Double,
    @SerializedName("image_url")
    val imageURL: String,
    val weight: Double,
    @SerializedName("generation_id")
    val generationId: Int,
    @SerializedName("generation_name")
    val generationName: String
)