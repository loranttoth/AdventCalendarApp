package com.loranttoth.adventcalendar.data.mappers

import com.loranttoth.adventcalendar.data.remote.ImageResult
import com.loranttoth.adventcalendar.domain.entity.AdventImageEntity

fun ImageResult.toAdventImageEntity(): AdventImageEntity {
    return AdventImageEntity(
        link = link,
        position = position,
        source = source,
        sourceUrl = sourceUrl,
        thumbnail = thumbnail,
        title = title
    )
}

fun AdventImageEntity.toData(): ImageResult {
    return ImageResult(
        link = link,
        position = position,
        source = source,
        sourceUrl = sourceUrl,
        thumbnail = thumbnail,
        title = title
    )
}