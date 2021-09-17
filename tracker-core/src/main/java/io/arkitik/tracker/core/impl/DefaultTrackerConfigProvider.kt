package io.arkitik.tracker.core.impl

import io.arkitik.tracker.core.annotation.TrackedMapping
import io.arkitik.tracker.core.annotation.TrackedRestController
import io.arkitik.tracker.core.config.TrackerConfig
import io.arkitik.tracker.core.model.TrackedPath
import io.arkitik.tracker.core.provider.TrackerConfigProvider
import io.arkitik.tracker.core.toTrackedPaths
import org.springframework.beans.factory.BeanFactoryUtils
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
class DefaultTrackerConfigProvider(
    trackerConfig: TrackerConfig,
    applicationContext: ApplicationContext,
) : TrackerConfigProvider {
    private val trackedPaths: MutableList<TrackedPath> = ArrayList(trackerConfig.tracked)
    override fun paths() = trackedPaths

    init {
        val beansOfTypeIncludingAncestors = BeanFactoryUtils.beansOfTypeIncludingAncestors(
            applicationContext,
            RequestMappingHandlerMapping::class.java,
            true,
            true
        )
        val requestMappingHandlerMapping = beansOfTypeIncludingAncestors["requestMappingHandlerMapping"]
        val handlerMethods = requestMappingHandlerMapping?.handlerMethods
        handlerMethods?.filter { entry ->
            entry.value.method.annotations.any {
                it.annotationClass.annotations.filterIsInstance<TrackedMapping>().isNotEmpty()
            } || entry.value.beanType.isAnnotationPresent(TrackedRestController::class.java)
        }?.map { entry ->
            entry.key.patternsCondition!!
                .patterns toTrackedPaths {
                entry.key.methodsCondition.methods.map {
                    HttpMethod.resolve(it.name)!!
                }
            }
        }?.forEach { trackedPaths.addAll(it) }
    }
}