package io.arkitik.tracker.annotation.based.filter

import io.arkitik.tracker.annotation.based.processor.AnnotationBasedTrackerProcessor
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
class TrackerAnnotationBasedRequestFilter(
    private val annotationBasedTrackerProcessor: AnnotationBasedTrackerProcessor,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        filterChain.doFilter(request, response)
        annotationBasedTrackerProcessor.execute(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest) = false
}