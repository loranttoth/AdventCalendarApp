package com.loranttoth.adventcalendar.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity
import com.loranttoth.myjobsapp.domain.entity.WindowStateEntity

@Database(
    entities = [AdventImageEntity::class, WindowStateEntity::class],
    version = 1
)
abstract class AdventDatabase : RoomDatabase() {
    abstract val dao : AdventDao
}