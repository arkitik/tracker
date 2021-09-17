package io.arkitik.tracker.core.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@TrackedMapping(method = [RequestMethod.PATCH])
annotation class TrackedPatchMapping(
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