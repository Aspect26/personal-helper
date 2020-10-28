package com.example.knowledgebase.db.models

data class BasicItem (
    val title: String,
    val subtitle: String,
    val image: String,
    val tagImages: Array<String>,
    val properties: Array<BasicItemProperty>
)