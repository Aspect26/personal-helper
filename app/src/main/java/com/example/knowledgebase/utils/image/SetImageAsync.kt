package com.example.knowledgebase.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import java.io.InputStream
import java.net.URL


object SetImageAsync {

    data class Dimensions(val height: Int, val width: Int)

    fun set(imageView: ImageView, imageString: String, dimensions: Dimensions? = null) {
        val (imageData, imageType) = ImageString.decode(imageString)

        when (imageType) {
            ImageString.ImageType.URL -> SetUrlImageTask(imageView, imageData, dimensions).execute()
            ImageString.ImageType.BASE64 -> SetBase64ImageTask(imageView, imageData, dimensions).execute()
            else -> throw NotImplementedError("Setting image for type '${imageType.name}' not yet implemented")
        }
    }

    class SetUrlImageTask(imageView: ImageView, imageUrl: String, dimensions: Dimensions?)
        : SetImageTask(imageView, imageUrl, dimensions) {

        override fun createImage(imageData: String): Bitmap? {
            val imageUrl = URL(imageData)
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

    }

    class SetBase64ImageTask(imageView: ImageView, base64String: String, dimensions: Dimensions?)
        : SetImageTask(imageView, base64String, dimensions) {

        override fun createImage(imageData: String): Bitmap? {
            val decodedString: ByteArray = Base64.decode(imageData, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }

    }

    abstract class SetImageTask(
        private val imageView: ImageView,
        private val imageData: String,
        private val dimensions: Dimensions?
    ) : AsyncTask<Void, Void, Bitmap?>() {

        override fun doInBackground(vararg params: Void?): Bitmap? {
            return createImage(imageData)
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result == null) {
                return
            }

            val scaledImage = if (dimensions != null) {
                Bitmap.createScaledBitmap(result, dimensions.width, dimensions.height, true)
            } else {
                result
            }

            imageView.setImageBitmap(scaledImage)
        }

        abstract fun createImage(imageData: String): Bitmap?
    }
}