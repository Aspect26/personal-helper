package com.example.knowledgebase.widget.specification.models

data class WidgetSettings(
    val widgetType: WidgetType,
    val itemType: ItemType
)

enum class ItemType {
    ROW,
    CARD
}