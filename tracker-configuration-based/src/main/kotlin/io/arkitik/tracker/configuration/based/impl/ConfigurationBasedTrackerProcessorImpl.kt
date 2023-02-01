package io.arkitik.tracker.configuration.based.impl

import io.arkitik.tracker.configuration.based.processor.ConfigurationBasedTrackerProcessor
import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
class ConfigurationBasedTrackerProcessorImpl(
    private val trackerProcessorUnits: List<ConfigurationBasedTrackerProcessor.TrackerProcessorUnit>,
) : ConfigurationBasedTrackerProcessor {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun execute(request: HttpServletRequest, response: HttpServletResponse) {
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