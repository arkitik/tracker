package io.arkitik.tracker.core.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@RequestMapping
annotation class TrackedMapping(
    /**
     * Alias for [RequestMapping.name].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val method: Array<RequestMethod> = [],

    /**
     * Alias for [RequestMapping.name].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val name: String = "",

    /**
     * Alias for [RequestMapping.value].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    vararg val value: String = [],

    /**
     * Alias for [RequestMapping.path].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val path: Array<String> = [],

    /**
     * Alias for [RequestMapping.params].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val params: Array<String> = [],

    /**
     * Alias for [RequestMapping.headers].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val headers: Array<String> = [],

    /**
     * Alias for [RequestMapping.consumes].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val consumes: Array<String> = [],

    /**
     * Alias for [RequestMapping.produces].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val produces: Array<String> = [],
)