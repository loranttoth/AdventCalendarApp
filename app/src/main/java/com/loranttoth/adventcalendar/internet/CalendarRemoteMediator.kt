package com.loranttoth.adventcalendar.internet

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.loranttoth.adventcalendar.data.mappers.toAdventImageEntity
import com.loranttoth.adventcalendar.domain.db.AdventDatabase
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CalendarRemoteMediator (
        private val adventDb: AdventDatabase,
        private val calendarApi: CalendarApi
    ) : RemoteMediator<Int, AdventImageEntity>() {
        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, AdventImageEntity>
        ): MediatorResult {
            return try {
                val loadKey = when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                        if (lastItem == null) {
                            1
                        } else {
                            (lastItem.position / state.config.pageSize) + 1
                        }
                    }
                }

                val imageResult = calendarApi.getImages(
                    apikey = CalendarApi.API_KEY,
                    query = "Christmas old postcard portrait",
                    tbm = "isch",
                    device = "mobile",
                   // num = 24
                )

                val images = imageResult.image_results.subList(0, 24)

                adventDb.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        adventDb.dao.clearAll()
                    }
                    val adventImageEntities = images.map { it.toAdventImageEntity() }
                    adventDb.dao.upsertAll(adventImageEntities)
                }

                MediatorResult.Success(
                    endOfPaginationReached = images.isEmpty()
                )
            } catch (e: IOException) {
                MediatorResult.Error(e)
            } catch (e: HttpException) {
                MediatorResult.Error(e)
            }
        }

    }