package com.example.knowledgebase.db.hardcoded

import com.example.knowledgebase.db.Database
import com.example.knowledgebase.db.hardcoded.data.beerDbData

object HardcodedDatabase : Database {

    override fun getWidgets(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getWidgetSpecification(widgetId: String): String {
        return beerDbData
    }

}