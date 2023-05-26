package io.arkitik.tracker.core.impl

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.slf4j.LoggerFactory
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class DefaultTrackerProcessor(
    private val trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
) : TrackerProcessor {
    companion object {
        private val logger = LoggerFactory.getLogger(DefaultTrackerProcessor::class.java)
    }

    override fun execute(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        trackerProcessorUnits.filter { processor ->
            processor.isSupported(request)
        }.forEach { processor ->
            processor.runCatching {
                execute(request, response)
            }.onFailure { error ->
                logger.warn(
                    "Filter wasn't applied properly, error will be ignored, cause [Processor: {}, Cause: {}]",
                    processor.javaClass.canonicalName,
                    error.message
                )
            }
        }
    }
}
