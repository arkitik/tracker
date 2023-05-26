package io.arkitik.tracker.core.reactive

import io.arkitik.tracker.core.processor.TrackerProcessor
import io.arkitik.tracker.core.reactive.function.ReactiveTrackerProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

/**
 * Created By Ibrahim Al-Tamimi 
 * Created At 1:31 PM, 26 , **Fri, May 2023**
 */
@Configuration
class TrackerReactiveConfiguration {
    @Bean
    @Primary
    fun reactiveTrackerProcessor(
        trackerProcessorUnits: List<TrackerProcessor.TrackerProcessorUnit>,
    ): TrackerProcessor = ReactiveTrackerProcessor(trackerProcessorUnits)

}