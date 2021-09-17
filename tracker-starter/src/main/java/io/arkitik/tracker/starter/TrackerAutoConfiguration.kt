package io.arkitik.tracker.starter

import io.arkitik.tracker.core.TrackerConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 30, **Fri Oct, 2020**
 * Project *radix* [https://arkitik.io]
 */
@Configuration
@Import(value = [TrackerConfiguration::class])
class TrackerAutoConfiguration