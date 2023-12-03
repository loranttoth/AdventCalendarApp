package com.loranttoth.adventcalendar.domain.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity
import com.loranttoth.myjobsapp.domain.entity.WindowStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
    interface AdventDao {
        @Upsert
        suspend fun upsertAll(images: List<AdventImageEntity>)
        @Query("SELECT * FROM imageentity")
        fun pagingSource(): PagingSource<Int, AdventImageEntity>
        @Query("DELETE FROM imageentity")
        suspend fun clearAll()

        @Upsert
        suspend fun insertWindowState(windowStateEntity: WindowStateEntity)
        @Query("SELECT * FROM windowstate")
        fun getWindowState(): Flow<List<WindowStateEntity>>
    }