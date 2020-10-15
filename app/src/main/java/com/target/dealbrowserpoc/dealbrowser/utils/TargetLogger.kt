package com.target.dealbrowserpoc.dealbrowser.utils

import android.util.Log
import com.target.dealbrowserpoc.dealbrowser.BuildConfig

object TargetLogger {
        private const val TAG_TARGET = "TARGET"
        private const val TAG_ERROR = "TARGET_ERROR"
        private const val TAG_DEBUG = "TARGET_DEBUG"
        private const val TAG_EVENT = "TARGET_EVENT"
        private const val TAG_WARN  = "TARGET_WARN"

        private var DEBUG_ON = BuildConfig.DEBUG

        private fun log(ls: LogState, tag: String?, msg: String) {
            if (DEBUG_ON) {
                when (ls) {
                    LogState.D -> Log.d(tag, msg)
                    LogState.I -> Log.i(tag, msg)
                    LogState.W -> Log.w(tag, msg)
                    LogState.E -> Log.e(tag, msg)
                }
            }
        }

        fun log(cls: Class<*>, ls: LogState, tag: String?, msg: String) {
            if(tag == null) return log(cls, ls, msg)
            if (DEBUG_ON) {
                when (ls) {
                    LogState.D -> Log.d(tag, "${cls.simpleName} -- $msg")
                    LogState.I -> Log.i(tag, "${cls.simpleName} -- $msg")
                    LogState.W -> Log.w(tag, "${cls.simpleName} -- $msg")
                    LogState.E -> Log.e(tag, "${cls.simpleName} -- $msg")
                }
            }
        }

        private fun log(cls: Class<*>, ls: LogState, msg: String) {
            if (DEBUG_ON) {
                when (ls) {
                    LogState.D -> Log.d(TAG_TARGET, "(${cls.simpleName}.kt:1) -- $msg")
                    LogState.I -> Log.i(TAG_TARGET, "(${cls.simpleName}.kt:1) -- $msg")
                    LogState.W -> Log.w(TAG_TARGET, "(${cls.simpleName}.kt:1) -- $msg")
                    LogState.E -> Log.e(TAG_TARGET, "(${cls.simpleName}.kt:1) -- $msg")
                }
            }
        }

        fun error(cls: Class<*>, msg: String) {
            if (DEBUG_ON) {
                Log.e(TAG_ERROR, "(${cls.simpleName}.kt:1) -- $msg")
            }
        }

        fun error(msg: String) {
            if (DEBUG_ON) {
                log(LogState.E, TAG_ERROR, msg)
            }
        }

        fun debug(msg: String) {
            if (DEBUG_ON) {
                log(LogState.D, TAG_DEBUG, msg)
            }
        }

        fun info(msg: String) {
            if (DEBUG_ON) {
                log(LogState.I, TAG_DEBUG, msg)
            }
        }

        fun event(msg: String) {
            if (DEBUG_ON) {
                log(LogState.I, TAG_EVENT, msg)
            }
        }

        fun warn(msg: String) {
            if (DEBUG_ON) {
                log(LogState.W, TAG_WARN, msg)
            }
        }
    
        enum class LogState {
            D, I, W, E
        }
    
}