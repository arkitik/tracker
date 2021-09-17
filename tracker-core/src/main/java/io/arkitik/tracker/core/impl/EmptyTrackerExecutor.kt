package io.arkitik.tracker.core.impl

import io.arkitik.tracker.core.executer.TrackerExecutor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.StreamUtils
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
class EmptyTrackerExecutor : TrackerExecutor {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    override fun execute(request: HttpServletRequest, response: HttpServletResponse) {
        logger.info(
            "Request: {}, Response: {}",
            StreamUtils.copyToString(ContentCachingRequestWrapper(request).inputStream, StandardCharsets.UTF_8),
            StreamUtils.copyToString(ContentCachingResponseWrapper(response).contentInputStream, StandardCharsets.UTF_8)
        )
    }
}