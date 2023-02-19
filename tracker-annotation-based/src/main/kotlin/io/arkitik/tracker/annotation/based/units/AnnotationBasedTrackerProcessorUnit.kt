package io.arkitik.tracker.annotation.based.units

import io.arkitik.tracker.annotation.based.annotation.TrackedMapping
import io.arkitik.tracker.annotation.based.annotation.TrackedRestController
import io.arkitik.tracker.annotation.based.model.TrackedPath
import io.arkitik.tracker.annotation.based.processor.AnnotationBasedTrackerProcessor
import io.arkitik.tracker.annotation.based.toTrackedPaths
import io.arkitik.tracker.core.processor.TrackerProcessor
import org.springframework.beans.factory.BeanFactoryUtils
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.util.PathMatcher
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import javax.servlet.http.HttpServletRequest

/**
 * Created By Mohammed Mohiesen
 * Created At **Wednesday **01**, February 2023**
 */
internal class AnnotationBasedTrackerProcessorUnit(
    applicationContext: ApplicationContext,
    private val pathMatcher: PathMatcher,
    private val annotationBasedTrackerProcessor: AnnotationBasedTrackerProcessor,
) : TrackerProcessor.TrackerProcessorUnit {
    private val trackedPaths: MutableList<TrackedPath> = ArrayList()

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

    override fun isSupported(request: HttpServletRequest): Boolean {
        return trackedPaths
            .filter {
                it.methods.contains(HttpMethod.resolve(request.method))
            }.any {
                with(pathMatcher) {
                    matchStart(request.servletPath, it.path)
                }
            }
    }

    override fun execute(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        annotationBasedTrackerProcessor.execute(request, response)
    }
}
