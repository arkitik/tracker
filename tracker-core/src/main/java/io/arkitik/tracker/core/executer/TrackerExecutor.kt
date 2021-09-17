package io.arkitik.tracker.core.executer

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 18 1:42 AM, **Sat, September 2021**
 * Project *tracker* [https://arkitik.io]
 */
interface TrackerExecutor {
    fun execute(
        request: HttpServletRequest,
        response: HttpServletResponse,
    )
}