package com.loranttoth.myjobsapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "windowstate")
data class WindowStateEntity(
    @PrimaryKey
    val day: Int
)
