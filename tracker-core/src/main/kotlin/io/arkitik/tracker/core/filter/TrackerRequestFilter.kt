package io.arkitik.tracker.core.filter

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
internal class TrackerRequestFilter(
    private val trackProcessor: TrackerProcessor,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val cachedRequest = ContentCachingRequestWrapper(request)
        val cachedResponse = ContentCachingResponseWrapper(response)
        filterChain.doFilter(cachedRequest, cachedResponse)
        trackProcessor.execute(cachedRequest, cachedResponse)
        cachedResponse.copyBodyToResponse()
    }

    override fun shouldNotFilter(request: HttpServletRequest) = false
}