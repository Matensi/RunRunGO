package com.matensi.runrungo.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    private val timeFormatter = SimpleDateFormat("HH:mm:ss")

    fun getTime(timeInMilles: Long): String{
        val cv = Calendar.getInstance()
        timeFormatter.timeZone = TimeZone.getTimeZone("UTC")
        cv.timeInMillis = timeInMilles
        return timeFormatter.format(cv.time)
    }
}