package com.example.test.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AboutDatabaseModel constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imageHref: String
)

fun List<AboutDatabaseModel>.asDomainModel(): List<AboutDatabaseModel> {
    return map {
        AboutDatabaseModel(
            title = it.title,
            description = it.description,
            imageHref = it.imageHref
        )
    }
}