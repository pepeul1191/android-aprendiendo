package pe.edu.ulima.aprendiendo.models.responses

import com.google.gson.annotations.SerializedName

data class UserValidateResponse(
    val id: Int,
    val name: String,
    val user: String,
    val email: String,
    @SerializedName("image_url")
    val imageURL: String
)
