package com.example.knowledgebase.beerdb.models

data class Beer (
    val title: String,
    val subtitle: String,
    val degrees: Int?,
    val percentage: Float,
    val bottleSize: Int,
    val year: Int,
    val country: Country,
)