package com.example.knowledgebase.utils.image

import java.lang.Exception

object ImageString {

    enum class ImageType { BASE64, URL }
    data class ImageData(val data: String, val type: ImageType)

    class ImageStringException(message: String, cause: Throwable? = null) : Exception(message, cause)

    fun decode(imageString: String): ImageData {
        val parts = imageString.split(":")
        if (parts.size == 1) {
            throw ImageStringException("Bad format of imagestring: $imageString")
        }

        val type = stringToType(parts[0])
        val data = parts.subList(1, parts.size).joinToString(":")

        return ImageData(data, type)
    }

    private fun stringToType(typeString: String): ImageType {
        return when (typeString) {
            "url" -> ImageType.URL
            "base64" -> ImageType.BASE64
            else -> throw ImageStringException("Unknown imagestring type: $typeString")
        }
    }
}