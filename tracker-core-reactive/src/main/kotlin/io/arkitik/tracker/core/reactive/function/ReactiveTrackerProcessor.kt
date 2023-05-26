package io.arkitik.tracker.core.reactive.function

import io.arkitik.tracker.core.processor.TrackerProcessor
import org.slf4j.LoggerFactory
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import reactor.kotlin.core.publisher.toFlux

/**
 * Created By Ibrahim Al-Tamimi
 * Created At **Friday **26**, May 2023**
 */
internal class ReactiveTrackerProcessor(
    private val trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
) : TrackerProcessor {
    companion object {
        private val logger = LoggerFactory.getLogger(ReactiveTrackerProcessor::class.java)
    }

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
                        processor.javaClass.simpleName,
                        error.message,
                    )
                }
            }
    }
}
