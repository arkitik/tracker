package io.arkitik.tracker.configuration.based.filter

import io.arkitik.tracker.configuration.based.processor.ConfigurationBasedTrackerProcessor
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
class TrackerConfigurationBasedRequestFilter(
    private val trackProcessor: ConfigurationBasedTrackerProcessor,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        filterChain.doFilter(request, response)
        trackProcessor.execute(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest) = false
}