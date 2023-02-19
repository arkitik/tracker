package io.arkitik.tracker.configuration.based.model

import org.springframework.http.HttpMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
interface TrackedPath {
    val path: String
    val methods: Set<HttpMethod>
}