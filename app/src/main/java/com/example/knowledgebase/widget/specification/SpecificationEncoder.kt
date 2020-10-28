package com.example.knowledgebase.widget.specification

import com.example.knowledgebase.db.models.BasicItem
import com.example.knowledgebase.db.models.BasicItemProperty
import com.example.knowledgebase.widget.specification.models.WidgetSettings
import com.example.knowledgebase.widget.specification.models.WidgetSpecification
import com.example.knowledgebase.widget.specification.models.WidgetType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object SpecificationEncoder {

    class SpecificationEncodingException(message: String, cause: Throwable? = null) : Exception(message, cause)

    fun encode(specification: String): WidgetSpecification {
        val specificationJson = JSONObject(specification)

        val widgetSettings = getWidgetSettings(specificationJson)
        val widgetData = getWidgetData(specificationJson)

        return WidgetSpecification(widgetSettings, widgetData)
    }

    private fun getWidgetSettings(widgetSpecification: JSONObject): WidgetSettings {
        val widgetSettingsObject = getObjectOrThrow(widgetSpecification, "widget")

        val widgetType = when (val widgetTypeRaw = getStringOrThrow(widgetSettingsObject, "type", "widget.")) {
            "basic" -> WidgetType.BASIC
            else -> throw SpecificationEncodingException("Unknown widget type '$widgetTypeRaw'")
        }

        return WidgetSettings(widgetType)
    }

    private fun getWidgetData(widgetSpecification: JSONObject): Array<BasicItem> {
        val dataRaw = getArrayOrThrow(widgetSpecification, "data")
        return Array(dataRaw.length()) {
            getBasicItem(dataRaw.getJSONObject(it))
        }
    }

    private fun getBasicItem(itemJson: JSONObject): BasicItem =
        BasicItem(
            if (itemJson.has("title")) itemJson.getString("title") else "",
            if (itemJson.has("subtitle")) itemJson.getString("subtitle") else "",
            if (itemJson.has("image")) itemJson.getString("image") else "",
            getBasicItemTagImages(itemJson),
            getBasicItemProperties(itemJson),
        )

    private fun getBasicItemTagImages(itemJson: JSONObject): Array<String> {
        if (!itemJson.has("tagImages")) {
            return arrayOf()
        }

        val tagImagesRaw = itemJson.getJSONArray("tagImages")
        return Array(tagImagesRaw.length()) {
            tagImagesRaw.getString(it)
        }
    }

    private fun getBasicItemProperties(itemJson: JSONObject): Array<BasicItemProperty> {
        if (!itemJson.has("properties")) {
            return arrayOf()
        }

        val propertiesObject = itemJson.getJSONObject("properties")
        val properties = propertiesObject.names() ?: return arrayOf()

        return Array(properties.length()) {
            val propertyName = properties.getString(it)
            val property = propertiesObject.get(propertyName)

            when (property) {
                is JSONObject -> {
                    BasicItemProperty(property.getString("label"), property.getString("value"))
                }
                is String -> {
                    BasicItemProperty(propertyName, property)
                }
                else -> {
                    throw SpecificationEncodingException("Property '$property' in '$itemJson is invalid.")
                }
            }
        }
    }

    private fun getObjectOrThrow(json: JSONObject, objectName: String, previousPath: String = ""): JSONObject {
        try {
            return json.getJSONObject(objectName)
        } catch (e: JSONException) {
            throw SpecificationEncodingException("The specification must contain object '$previousPath$objectName'", e)
        }
    }

    private fun getArrayOrThrow(json: JSONObject, arrayName: String, previousPath: String = ""): JSONArray {
        try {
            return json.getJSONArray(arrayName)
        } catch (e: JSONException) {
            throw SpecificationEncodingException("The specification must contain array '$previousPath$arrayName'", e)
        }
    }

    private fun getStringOrThrow(json: JSONObject, propertyName: String, previousPath: String = ""): String {
        try {
            return json.getString(propertyName)
        } catch (e: JSONException) {
            throw SpecificationEncodingException("The specification must contain string '$previousPath$propertyName'", e)
        }
    }
}