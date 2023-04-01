package pe.edu.ulima.aprendiendo.models.requests

data class UserValidateRequest(
    val user: String,
    var password: String
)
