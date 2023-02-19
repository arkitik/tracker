package io.arkitik.tracker.annotation.based.model

import org.springframework.http.HttpMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */

data class TrackedPath(
    val path: String,
    val methods: Set<HttpMethod>,
)