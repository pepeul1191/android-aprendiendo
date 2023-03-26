package pe.edu.ulima.aprendiendo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.ulima.aprendiendo.models.views.UploadModel

class UploadViewModel: ViewModel() {
    val uploadModel = MutableLiveData<UploadModel>()

    fun init(){
        uploadModel.postValue(UploadModel("", 0, ""))
    }

    fun updateImgRoute(filePath: String){
        var model = uploadModel.value
        if (model != null){
            model.imgRoute = filePath
            uploadModel.postValue(model!!)
        }
    }

    fun updateExtraData(extraData: String){
        var model = uploadModel.value
        if (model != null){
            model.extraData = extraData
            uploadModel.postValue(model!!)
        }
    }
}