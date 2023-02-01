package io.arkitik.tracker.annotation.based

import io.arkitik.tracker.annotation.based.impl.TrackedPathImpl
import io.arkitik.tracker.annotation.based.model.TrackedPath
import org.springframework.http.HttpMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */

infix fun String.toTrackedPath(methods: Iterable<HttpMethod>): TrackedPath =
    TrackedPathImpl(this, methods.toSet())

infix fun Collection<String>.toTrackedPaths(methods: Iterable<HttpMethod>) = map {
    it.toTrackedPath(methods)
}

infix fun Collection<String>.toTrackedPaths(provider: () -> List<HttpMethod>) =
    toTrackedPaths(provider())
