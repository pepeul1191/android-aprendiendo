package pe.edu.ulima.aprendiendo.services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    @Multipart
    @POST("/upload/demo")
    fun uploadFile(
        @Part file: MultipartBody.Part,
        @Part("extra_data") extraData: RequestBody
    ): Call<String>
}