package pe.edu.ulima.aprendiendo.services

import pe.edu.ulima.aprendiendo.models.requests.UserCreateRequest
import pe.edu.ulima.aprendiendo.models.requests.UserValidateRequest
import pe.edu.ulima.aprendiendo.models.responses.UserValidateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user/validate")
    fun validate(@Body requestModel: UserValidateRequest): Call<UserValidateResponse>

    @POST("/user/create")
    fun create(@Body requestModel: UserCreateRequest): Call<String>
}