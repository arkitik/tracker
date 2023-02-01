package io.arkitik.tracker.core.config

import io.arkitik.tracker.core.model.TrackedPath
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.HttpMethod

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@ConfigurationProperties("arkitik.tracker.config")
@ConstructorBinding
data class TrackerConfig(
    val tracked: List<TrackedPathImpl> = arrayListOf(),
) {
    @ConstructorBinding
    data class TrackedPathImpl(
        override val path: String,
        override val methods: Set<HttpMethod> = HashSet(),
    ) : TrackedPath
}
