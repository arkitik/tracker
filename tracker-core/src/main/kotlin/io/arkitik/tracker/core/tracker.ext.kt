package io.arkitik.tracker.core

import io.arkitik.tracker.core.config.TrackerConfig
import io.arkitik.tracker.core.model.TrackedPath
import org.springframework.http.HttpMethod

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */

infix fun String.toTrackedPath(methods: Iterable<HttpMethod>): TrackedPath =
    TrackerConfig.TrackedPathImpl(this, methods.toSet())

infix fun Collection<String>.toTrackedPaths(methods: Iterable<HttpMethod>) = map {
    it.toTrackedPath(methods)
}

infix fun Collection<String>.toTrackedPaths(provider: () -> List<HttpMethod>) =
    toTrackedPaths(provider())
