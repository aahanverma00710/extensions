package com.avcoding.utils_ext

import android.util.Log

inline fun <A> tryOrNull(message: String? = null, operation: () -> A): A? {
    return try {
        operation()
    } catch (any: Throwable) {
        if (message != null) {
            Log.e(message,any.message ?: "")
        }
        null
    }
}
