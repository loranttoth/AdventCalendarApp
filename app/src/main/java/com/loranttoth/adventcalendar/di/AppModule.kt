package com.loranttoth.adventcalendar.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.loranttoth.adventcalendar.domain.db.AdventDao
import com.loranttoth.adventcalendar.domain.db.AdventDatabase
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity
import com.loranttoth.adventcalendar.internet.CalendarApi
import com.loranttoth.adventcalendar.internet.CalendarRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAdventDao(adventDatabase: AdventDatabase): AdventDao
            = adventDatabase.dao

    @Provides
    @Singleton
    fun provideAdventDataBase(@ApplicationContext context: Context): AdventDatabase {
        return Room.databaseBuilder(
            context,
            AdventDatabase::class.java,
            "adventcal.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAdventApi(): CalendarApi {

        val client =  OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(CalendarApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CalendarApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAdventPager(
        adventDb: AdventDatabase,
        adventApi: CalendarApi
    ): Pager<Int, AdventImageEntity> {
        return Pager(
            config = PagingConfig(pageSize = 24),
            remoteMediator = CalendarRemoteMediator(
                adventDb = adventDb,
                calendarApi = adventApi
            ),
            pagingSourceFactory = {
               adventDb.dao.pagingSource()
            }
        )
    }
}