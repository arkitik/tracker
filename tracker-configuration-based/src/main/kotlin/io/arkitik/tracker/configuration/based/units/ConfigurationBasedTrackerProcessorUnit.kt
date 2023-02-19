package io.arkitik.tracker.configuration.based.units

import io.arkitik.tracker.configuration.based.config.TrackerConfig
import io.arkitik.tracker.configuration.based.processor.ConfigurationBasedTrackerProcessor
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.http.HttpMethod
import org.springframework.util.PathMatcher
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.http.HttpServletRequest

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class ConfigurationBasedTrackerProcessorUnit(
    private val pathMatcher: PathMatcher,
    private val trackerConfig: TrackerConfig,
    private val configurationBasedTrackerProcessor: ConfigurationBasedTrackerProcessor,
) : TrackerProcessor.TrackerProcessorUnit {
    override fun isSupported(request: HttpServletRequest): Boolean {
        return trackerConfig.tracked
            .filter {
                it.methods.contains(HttpMethod.resolve(request.method)!!)
            }.any {
                with(pathMatcher) {
                    matchStart(request.servletPath, it.path)
                }
            }
    }

    override fun execute(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        configurationBasedTrackerProcessor.execute(request, response)
    }
}
