package com.avcoding.utils_ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import java.io.OutputStream
import kotlin.math.roundToInt

fun Context.getDrawableAsSpannable(@DrawableRes drawableRes: Int, alignment: Int = ImageSpan.ALIGN_BOTTOM): Spannable {
    return SpannableString(" ").apply {
        val span = ContextCompat.getDrawable(this@getDrawableAsSpannable, drawableRes)?.let {
            it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
            ImageSpan(it, alignment)
        }
        setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}

fun Context.getResTintedDrawable(@DrawableRes drawableRes: Int, @ColorRes tint: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f): Drawable? {
    return getTintedDrawable(drawableRes, ContextCompat.getColor(this, tint), alpha)
}

fun Context.getTintedDrawable(
        @DrawableRes drawableRes: Int,
        @ColorInt tint: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1f
) = ContextCompat.getDrawable(this, drawableRes)
        ?.mutate()
        ?.also { drawable ->
            drawable.setTint(tint)
            alpha.let {
                drawable.alpha = it.toAndroidAlpha()
            }
        }

private fun Float.toAndroidAlpha(): Int {
    return (this * 255).roundToInt()
}


/**
 * Open Uri in truncate mode to make sure we don't partially overwrite content when we get passed a Uri to an existing file.
 */
fun Context.safeOpenOutputStream(uri: Uri): OutputStream? {
    return contentResolver.openOutputStream(uri, "wt")
}

