package io.arkitik.tracker.core

import io.arkitik.tracker.core.config.TrackerConfig
import io.arkitik.tracker.core.contract.TrackerVerifier
import io.arkitik.tracker.core.executer.TrackerExecutor
import io.arkitik.tracker.core.filter.TrackerRequestFilter
import io.arkitik.tracker.core.impl.DefaultTrackerConfigProvider
import io.arkitik.tracker.core.impl.DefaultTrackerVerifier
import io.arkitik.tracker.core.impl.EmptyTrackerExecutor
import io.arkitik.tracker.core.provider.TrackerConfigProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
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
    @ConditionalOnMissingBean(value = [TrackerExecutor::class])
    fun trackerExecutor(): TrackerExecutor = EmptyTrackerExecutor()

    @Bean
    @ConditionalOnMissingBean(value = [TrackerConfigProvider::class])
    fun trackerConfigProvider(
        trackerConfig: TrackerConfig,
        applicationContext: ApplicationContext,
    ): TrackerConfigProvider = DefaultTrackerConfigProvider(trackerConfig, applicationContext)

    @Bean
    @ConditionalOnMissingBean(value = [TrackerVerifier::class])
    fun trackerVerifier(
        trackerConfigProvider: TrackerConfigProvider,
        pathMatcher: PathMatcher,
    ): TrackerVerifier = DefaultTrackerVerifier(trackerConfigProvider, pathMatcher)

    @Bean
    fun trackerRequestFilter(
        trackerExecutor: TrackerExecutor,
        trackerVerifier: TrackerVerifier,
    ) = TrackerRequestFilter(trackerExecutor, trackerVerifier)
}