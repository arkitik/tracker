package io.arkitik.tracker.annotation.based.impl

import io.arkitik.tracker.annotation.based.model.TrackedPath
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.HttpMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
@ConstructorBinding
data class TrackedPathImpl(
    override val path: String,
    override val methods: Set<HttpMethod> = HashSet(),
) : TrackedPath