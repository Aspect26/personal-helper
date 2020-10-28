package com.example.knowledgebase.widget.specification.models

import com.example.knowledgebase.db.models.BasicItem

data class WidgetSpecification(val widgetSettings: WidgetSettings, val widgetData: Array<BasicItem>)
