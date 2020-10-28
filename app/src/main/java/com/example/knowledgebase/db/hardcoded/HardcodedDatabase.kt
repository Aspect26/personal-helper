package com.example.knowledgebase.db.hardcoded

import com.example.knowledgebase.db.Database
import com.example.knowledgebase.db.hardcoded.data.beerDbData
import com.example.knowledgebase.db.hardcoded.data.recipesData
import com.example.knowledgebase.db.hardcoded.data.widgets
import com.example.knowledgebase.db.models.Widget

object HardcodedDatabase : Database {

    override fun getWidgets(): Array<Widget> {
        return widgets
    }

    override fun getWidgetSpecification(widgetId: String): String {
        return when (widgetId) {
            "beerDb" -> beerDbData
            "recipes" -> recipesData
            else -> "{ \"widget\": { \"type\": \"basic\" }, \"data\": [] }"
        }
    }

}