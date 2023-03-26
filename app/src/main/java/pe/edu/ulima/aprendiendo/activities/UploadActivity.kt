package pe.edu.ulima.aprendiendo.activities

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pe.edu.ulima.aprendiendo.R
import pe.edu.ulima.aprendiendo.configs.BackendClient
import pe.edu.ulima.aprendiendo.databinding.ActivityUploadBinding
import pe.edu.ulima.aprendiendo.helpers.FileHelper
import pe.edu.ulima.aprendiendo.services.UploadService
import pe.edu.ulima.aprendiendo.viewmodels.UploadViewModel
import java.io.File
import kotlin.concurrent.thread

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private val uploadViewModel: UploadViewModel by viewModels()
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // model and observer
        uploadViewModel.init()
        uploadViewModel.uploadModel.observe(this, Observer { currentModel ->
            println("MODEL UPLOADED")
            println(currentModel.toString())
        })
        // events
        binding.btnEditProfileImg.setOnClickListener{
            val getIntent = Intent(ACTION_GET_CONTENT)
            getIntent.type = "image/*"
            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickIntent.type = "image/*"
            val chooserIntent = Intent.createChooser(getIntent, "Seleccione una imagen")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
            startActivityForResult(chooserIntent, 1)
        }
        binding.bUpload.setOnClickListener(){
            // upload model
            uploadViewModel.updateExtraData(binding.etExtraData.text.toString())
            val model = uploadViewModel.uploadModel.value
            // send file
            val file = File("/data/data/pe.edu.ulima.aprendiendo/files/6002057493.jpg")
            val fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name, fileRequestBody)
                .addFormDataPart("extra_data", model?.extraData)
                .build()
            val filePart = requestBody.part(0)
            val extraDataBody = RequestBody.create(MediaType.parse("text/plain"), model?.extraData)


            val apiService = BackendClient.buildService(UploadService::class.java)
            println("0 ++++++++++++++++++++++++++++++++++")
            thread{
                try {
                    val response = apiService.uploadFile(filePart, extraDataBody).execute()
                    if (response.isSuccessful) {
                        // Log.d("PokemonListViewModel", response.body().toString())
                        println("1 ++++++++++++++++++++++++++++++++++")
                        println(response.body())
                        println("2 ++++++++++++++++++++++++++++++++++")
                        // var newPokemonTypeList = generatePokemonTypeList()
                    } else {
                        // Handle error
                        activity.runOnUiThread{
                            Toast.makeText(
                                activity,
                                "Error: Problemas al enviar el formulario",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    activity.runOnUiThread{
                        Toast.makeText(
                            activity,
                            "Error HTTP: No se pudo conectar con el servicio",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(data == null){
                return;
            }else{
                val uri: Uri? = data.data
                if (uri != null){
                    // show image
                    binding.ivImage.setImageURI(uri)
                    // move image to internal storage
                    FileHelper.moveFileToAppStorage(this, uri)
                    // update model
                    val filePath = FileHelper.getRealPathFromUri(this, uri)
                    // val extension = FileHelper.getFileExtensionFromUri(this, uri)
                    uploadViewModel.updateImgRoute(filePath.toString())
                }
            }
        }
    }
}