package io.arkitik.tracker.configuration.based

import io.arkitik.tracker.configuration.based.config.TrackerConfig
import io.arkitik.tracker.configuration.based.impl.ConfigurationBasedTrackerProcessorImpl
import io.arkitik.tracker.configuration.based.processor.ConfigurationBasedTrackerProcessor
import io.arkitik.tracker.configuration.based.units.ConfigurationBasedTrackerProcessorUnit
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.AntPathMatcher
import org.springframework.util.PathMatcher

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
@Configuration
@EnableConfigurationProperties(value = [TrackerConfig::class])
class TrackerConfigurationBasedConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = [PathMatcher::class])
    fun pathMatcher(): PathMatcher = AntPathMatcher()

    @Bean
    fun configurationBasedTrackerProcessor(
        trackerProcessorUnits: List<ConfigurationBasedTrackerProcessor.TrackerProcessorUnit>,
    ): ConfigurationBasedTrackerProcessor =
        ConfigurationBasedTrackerProcessorImpl(trackerProcessorUnits)

    @Bean
    fun configurationBasedTrackerProcessorUnit(
        pathMatcher: PathMatcher,
        configurationBasedTrackerProcessor: ConfigurationBasedTrackerProcessor,
        trackerConfig: TrackerConfig,
    ): TrackerProcessor.TrackerProcessorUnit =
        ConfigurationBasedTrackerProcessorUnit(
            pathMatcher = pathMatcher,
            trackerConfig = trackerConfig,
            configurationBasedTrackerProcessor = configurationBasedTrackerProcessor
        )
}
