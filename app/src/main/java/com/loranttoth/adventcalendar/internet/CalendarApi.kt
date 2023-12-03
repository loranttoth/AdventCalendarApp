package com.loranttoth.adventcalendar.internet

import com.loranttoth.adventcalendar.data.remote.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarApi {

    @GET(END_POINT)
    suspend fun getImages(
        @Query("apikey") apikey: String,
        @Query("q") query: String,
        @Query("tbm") tbm: String,
        @Query("device") device: String,

    ): ImageResponse

    companion object {
        const val BASE_URL = "https://app.zenserp.com"
        const val END_POINT = "api/v2/search"
        const val API_KEY = "YOUR_API_KEY"
    }
}