package io.arkitik.tracker.core.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RestController

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RestController
annotation class TrackedRestController(
    @get:AliasFor(annotation = RestController::class)
    val value: String = "",
)