package io.arkitik.tracker.annotation.based.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@TrackedMapping(method = [RequestMethod.DELETE])
annotation class TrackedDeleteMapping(
    /**
     * Alias for [TrackedMapping.name].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val name: String = "",

    /**
     * Alias for [TrackedMapping.value].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    vararg val value: String = [],

    /**
     * Alias for [TrackedMapping.path].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val path: Array<String> = [],

    /**
     * Alias for [TrackedMapping.params].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val params: Array<String> = [],

    /**
     * Alias for [TrackedMapping.headers].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val headers: Array<String> = [],

    /**
     * Alias for [TrackedMapping.consumes].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val consumes: Array<String> = [],

    /**
     * Alias for [TrackedMapping.produces].
     */
    @get:AliasFor(annotation = TrackedMapping::class)
    val produces: Array<String> = [],
)