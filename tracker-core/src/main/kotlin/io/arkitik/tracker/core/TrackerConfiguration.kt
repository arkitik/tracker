package io.arkitik.tracker.core

import io.arkitik.tracker.core.config.TrackerConfig
import io.arkitik.tracker.core.filter.TrackerRequestFilter
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.AntPathMatcher
import org.springframework.util.PathMatcher

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@Configuration
@EnableConfigurationProperties(value = [TrackerConfig::class])
class TrackerConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = [PathMatcher::class])
    fun pathMatcher(): PathMatcher = AntPathMatcher()

    @Bean
    fun trackerRequestFilter(
        trackProcessor: TrackerProcessor,
    ) = TrackerRequestFilter(trackProcessor)
}
