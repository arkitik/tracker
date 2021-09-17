package io.arkitik.tracker.core.impl

import io.arkitik.tracker.core.contract.TrackerVerifier
import io.arkitik.tracker.core.provider.TrackerConfigProvider
import org.springframework.http.HttpMethod
import org.springframework.util.PathMatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
class DefaultTrackerVerifier(
    private val trackerConfigProvider: TrackerConfigProvider,
    private val pathMatcher: PathMatcher,
) : TrackerVerifier {
    override fun HttpServletRequest.shouldTrack(): Boolean {
        HttpServletRequestWrapper(this)
        return trackerConfigProvider
            .paths()
            .filter {
                it.methods.contains(HttpMethod.resolve(method))
            }.any {
                with(pathMatcher) {
                    match(it.path, servletPath) && it.methods == it.methods
                }
            }
    }
}