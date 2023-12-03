package com.loranttoth.adventcalendar.data.remote

data class Query(
    val apikey: String,
    val device: String,
    val num: String,
    val q: String,
    val tbm: String,
    val url: String
)