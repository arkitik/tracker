package io.arkitik.tracker.configuration.based.config

import io.arkitik.tracker.configuration.based.model.TrackedPath
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.HttpMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
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
