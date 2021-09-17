package io.arkitik.tracker.core.contract

import javax.servlet.http.HttpServletRequest

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
interface TrackerVerifier {
    fun HttpServletRequest.shouldTrack(): Boolean
}