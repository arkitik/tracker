package io.arkitik.tracker.core.model

import org.springframework.http.HttpMethod

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
interface TrackedPath {
    val path: String
    val methods: Set<HttpMethod>
}