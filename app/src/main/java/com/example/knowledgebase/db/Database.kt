package com.example.knowledgebase.db

import com.example.knowledgebase.db.models.Widget

interface Database {

    fun getWidgets(): Array<Widget>

    fun getWidgetSpecification(widgetId: String): String

}