package io.arkitik.tracker.core.filter

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
class TrackerRequestFilter(
    private val trackProcessor: TrackerProcessor,
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