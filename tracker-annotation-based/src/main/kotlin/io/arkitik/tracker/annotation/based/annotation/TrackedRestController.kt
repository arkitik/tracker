package io.arkitik.tracker.annotation.based.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RestController

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RestController
annotation class TrackedRestController(
    @get:AliasFor(annotation = RestController::class)
    val value: String = "",
)