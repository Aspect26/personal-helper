package com.example.knowledgebase.widget.models

data class BasicItem (
    val title: String,
    val subtitle: String,
    val image: String,
    val tagImages: Array<String>,
    val properties: Array<BasicItemProperty>
)