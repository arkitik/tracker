package io.arkitik.tracker.annotation.based.processor

import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.http.HttpServletRequest

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
fun interface AnnotationBasedTrackerProcessor {
    fun execute(
        request: ContentCachingRequestWrapper,
        response: ContentCachingResponseWrapper,
    )

    interface TrackerProcessorUnit {
        fun isSupported(request: HttpServletRequest): Boolean
        fun execute(
            request: ContentCachingRequestWrapper,
            response: ContentCachingResponseWrapper,
        )
    }
}
