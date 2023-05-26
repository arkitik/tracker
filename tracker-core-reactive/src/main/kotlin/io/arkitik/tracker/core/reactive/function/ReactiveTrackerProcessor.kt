package io.arkitik.tracker.core.reactive.function

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.slf4j.LoggerFactory
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import reactor.kotlin.core.publisher.toFlux

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class ReactiveTrackerProcessor(
    private val trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
) : TrackerProcessor {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun execute(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        trackerProcessorUnits.toFlux()
            .filter { processor ->
                processor.isSupported(request)
            }.subscribe { processor ->
                processor.runCatching {
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
