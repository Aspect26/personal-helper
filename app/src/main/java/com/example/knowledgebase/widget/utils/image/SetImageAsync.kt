package com.example.knowledgebase.widget.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import java.io.InputStream
import java.net.URL


object SetImageAsync {

    fun set(imageView: ImageView, imageString: String) {
        val (imageData, imageType) = ImageString.decode(imageString)

        when (imageType) {
            ImageString.ImageType.URL -> SetUrlImageTask(imageView, URL(imageData)).execute()
            ImageString.ImageType.BASE64 -> SetBase64ImageTask(imageView, imageData).execute()
            else -> throw NotImplementedError("Setting image for type '${imageType.name}' not yet implemented")
        }
    }

    class SetUrlImageTask(private val imageView: ImageView, private val imageUrl: URL) : AsyncTask<Void, Void, Bitmap?>() {

        override fun doInBackground(vararg p0: Void?): Bitmap? {
            var image: Bitmap? = null

            try {
                val inputStream: InputStream = imageUrl.openStream()
                image = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.i("widget", e.message ?: "")
                e.printStackTrace()
            }

            return image
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }
    }

    class SetBase64ImageTask(private val imageView: ImageView, private val base64String: String) : AsyncTask<Void, Void, Bitmap?>() {

        override fun doInBackground(vararg params: Void?): Bitmap? {
            val decodedString: ByteArray = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }
    }
}