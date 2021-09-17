package io.arkitik.tracker.core.provider

import io.arkitik.tracker.core.model.TrackedPath

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
interface TrackerConfigProvider {
    fun paths(): List<TrackedPath>
}