package me.saechimdaeki.playground

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerWithKotlinFile {
    companion object {
        fun getLogger(s: String) : Logger {
            return LoggerFactory.getLogger(s)
        }
    }
}