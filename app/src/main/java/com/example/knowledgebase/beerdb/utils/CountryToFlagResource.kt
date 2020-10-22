package com.example.knowledgebase.beerdb.utils

import com.example.knowledgebase.R
import com.example.knowledgebase.beerdb.models.Country

object CountryToFlagResource {

    fun get(country: Country): Int =
        when (country) {
            Country.CZE -> R.drawable.flag_cz
            Country.BEL -> R.drawable.flag_be
            else -> R.drawable.ic_country_flag_empty
        }
}