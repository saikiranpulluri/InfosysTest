package com.example.test.network

import com.example.test.database.AboutDatabaseModel
import com.example.test.domain.AboutDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class About(
    val title: String,
    @Json(name = "rows") val rows: List<AboutRow>,
)

@JsonClass(generateAdapter = true)
data class AboutRow(
    val title: String?,
    val description: String?,
    val imageHref: String?
)

fun About.asDomainModel(): List<AboutDomainModel> {
    return rows.map {
        AboutDomainModel(
            title = it.title ?: "",
            description = it.description ?: "",
            imageHref = it.imageHref ?: ""
        )
    }
}

fun About.asDatabaseModel(): List<AboutDatabaseModel> {
    return rows.map {
        AboutDatabaseModel(
            title = it.title ?: "",
            description = it.description ?: "",
            imgHref = it.imageHref ?: ""
        )
    }
}




