package pe.edu.ulima.aprendiendo.models.requests

data class UserCreateRequest(
    val user: String,
    var password: String,
    val email: String
)
