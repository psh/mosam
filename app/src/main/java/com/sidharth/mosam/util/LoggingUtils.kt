package com.sidharth.mosam.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.platformLogWriter

fun initLogging() {
    Logger.setTag("weather")
    Logger.setMinSeverity(Severity.Verbose)
    Logger.setLogWriters(platformLogWriter())
}