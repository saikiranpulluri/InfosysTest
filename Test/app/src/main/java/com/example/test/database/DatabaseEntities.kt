package com.example.test.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.AboutDomainModel

@Entity
data class AboutDatabaseModel constructor(
    @PrimaryKey
    val title: String,
    val description: String,
    val imgHref: String
)

fun List<AboutDatabaseModel>.asDomainModel(): List<AboutDomainModel> {
    return map {
        AboutDomainModel(
            title = it.title,
            description = it.description,
            imageHref = it.imgHref
        )
    }
}