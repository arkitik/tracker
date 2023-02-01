package io.arkitik.tracker.core.processor

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
fun interface TrackerProcessor {
    fun execute(
        request: HttpServletRequest,
        response: HttpServletResponse,
    )

    interface TrackerProcessorUnit {
        fun isSupported(request: HttpServletRequest): Boolean
        fun execute(
            request: HttpServletRequest,
            response: HttpServletResponse,
        )
    }
}
