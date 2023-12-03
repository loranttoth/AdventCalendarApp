package com.loranttoth.adventcalendar.data.remote

data class ImageResponse(
    val image_results: List<ImageResult>,
    val query: Query,
    val related_searches: List<Any>
)