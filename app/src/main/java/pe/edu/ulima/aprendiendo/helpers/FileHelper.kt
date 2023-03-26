package pe.edu.ulima.aprendiendo.helpers

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

class FileHelper {
    companion object {
        fun getRealPathFromUri(activity: Activity, uri: Uri): String? {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = activity.contentResolver.query(uri, projection, null, null, null)
            if (cursor != null) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                return filePath
            }
            return uri.path
        }

        fun getFileExtensionFromUri(activity: Activity, uri: Uri): String {
            var extension: String = ""
            val realPath: String? = getRealPathFromUri(activity, uri)
            if (realPath != null){
                extension = realPath.split(".").last()
            }
            return extension
        }

        fun randomFileName(extension: String): String{
            val charPool: List<Char> = ('0'..'9').toList()
            val randomString = (1..10)
                .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
            return "$randomString.$extension"
        }

        fun moveFileToAppStorage(activity: Activity, uri: Uri){
            val inputStream = activity.contentResolver.openInputStream(uri)
            val filename = randomFileName(
                getFileExtensionFromUri(activity, uri)
            )
            val outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE)
            val buffer = ByteArray(1024)
            if (inputStream != null){
                var bytesRead = inputStream.read(buffer)
                while (bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead)
                    bytesRead = inputStream.read(buffer)
                }
                inputStream.close()
            }
            outputStream.close()
        }
    }
}