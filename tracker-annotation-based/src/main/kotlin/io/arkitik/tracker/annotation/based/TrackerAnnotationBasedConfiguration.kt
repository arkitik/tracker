package io.arkitik.tracker.annotation.based

import io.arkitik.tracker.annotation.based.impl.AnnotationBasedTrackerProcessorImpl
import io.arkitik.tracker.annotation.based.processor.AnnotationBasedTrackerProcessor
import io.arkitik.tracker.annotation.based.units.AnnotationBasedTrackerProcessorUnit
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.AntPathMatcher
import org.springframework.util.PathMatcher

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
@Configuration
class TrackerAnnotationBasedConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = [PathMatcher::class])
    fun pathMatcher(): PathMatcher = AntPathMatcher()

    @Bean
    fun annotationBasedTrackerProcessor(
        trackerProcessorUnits: List<AnnotationBasedTrackerProcessor.TrackerProcessorUnit>,
    ): AnnotationBasedTrackerProcessor =
        AnnotationBasedTrackerProcessorImpl(trackerProcessorUnits)

    @Bean
    fun annotationBasedTrackerProcessorUnit(
        applicationContext: ApplicationContext,
        pathMatcher: PathMatcher,
        annotationBasedTrackerProcessor: AnnotationBasedTrackerProcessor,
    ): TrackerProcessor.TrackerProcessorUnit =
        AnnotationBasedTrackerProcessorUnit(
            applicationContext = applicationContext,
            pathMatcher = pathMatcher,
            annotationBasedTrackerProcessor = annotationBasedTrackerProcessor
        )
}
