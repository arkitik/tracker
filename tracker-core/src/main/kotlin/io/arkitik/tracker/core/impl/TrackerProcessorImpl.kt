package io.arkitik.tracker.core.impl

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.slf4j.LoggerFactory
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class TrackerProcessorImpl(
    private val trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
) : TrackerProcessor {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun execute(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        trackerProcessorUnits.filter { processor ->
            processor.isSupported(request)
        }.forEach {
            it.runCatching {
                execute(request, response)
            }.onFailure { error ->
                logger.warn(
                    "Filter wasn't applied properly, error will be ignored, cause [Processor: {}, Cause: {}]",
                    error,
                    error.message
                )
            }
        }
    }
}
