package com.example.knowledgebase.db

interface Database {

    fun getWidgets(): Array<String>

    fun getWidgetSpecification(widgetId: String): String

}