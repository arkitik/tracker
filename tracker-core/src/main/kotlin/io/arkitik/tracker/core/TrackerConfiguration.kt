package io.arkitik.tracker.core

import io.arkitik.tracker.core.filter.TrackerRequestFilter
import io.arkitik.tracker.core.impl.DefaultTrackerProcessor
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
@Configuration
class TrackerConfiguration {
    @Bean
    fun trackerRequestFilter(
        trackProcessor: TrackerProcessor,
    ): OncePerRequestFilter = TrackerRequestFilter(trackProcessor)

    @Bean
    @ConditionalOnMissingBean
    fun defaultTrackerProcessor(
        trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
    ): TrackerProcessor = DefaultTrackerProcessor(trackerProcessorUnits)
}
