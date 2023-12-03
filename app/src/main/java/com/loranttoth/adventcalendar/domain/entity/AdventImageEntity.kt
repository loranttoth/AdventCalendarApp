package com.loranttoth.adventcalendar.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageentity")
data class AdventImageEntity(
    @PrimaryKey
    val position: Int,
    val link: String,
    val source: String,
    val sourceUrl: String,
    val thumbnail: String,
    val title: String
)