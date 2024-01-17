package com.avcoding.utils_ext

import android.os.Bundle
import android.os.Parcelable

fun Parcelable?.toBundle(): Bundle? {
    return this?.let { Bundle().apply { putParcelable("", it) } }
}
