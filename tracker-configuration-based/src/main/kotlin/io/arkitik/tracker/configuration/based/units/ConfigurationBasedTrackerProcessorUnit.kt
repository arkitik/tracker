package io.arkitik.tracker.configuration.based.units

import io.arkitik.tracker.configuration.based.config.TrackerConfig
import io.arkitik.tracker.configuration.based.processor.ConfigurationBasedTrackerProcessor
import org.springframework.http.HttpMethod
import org.springframework.util.PathMatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class ConfigurationBasedTrackerProcessorUnit(
    private val pathMatcher: PathMatcher,
    private val configurationBasedTrackerProcessor: ConfigurationBasedTrackerProcessor,
    private val trackerConfig: TrackerConfig,
) : ConfigurationBasedTrackerProcessor.TrackerProcessorUnit {
    override fun isSupported(request: HttpServletRequest): Boolean {
        return trackerConfig.tracked
            .filter {
                it.methods.contains(HttpMethod.resolve(request.method))
            }.any {
                with(pathMatcher) {
                    matchStart(request.servletPath, it.path)
                }
            }
    }

    override fun execute(request: HttpServletRequest, response: HttpServletResponse) {
        configurationBasedTrackerProcessor.execute(request, response)
    }
}
