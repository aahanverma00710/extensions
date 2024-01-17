package com.avcoding.utils_ext

import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible

/**
 * Set a text in the TextView, or set visibility to GONE if the text is null.
 */
fun TextView.setTextOrHide(newText: CharSequence?, hideWhenBlank: Boolean = true, vararg relatedViews: View = emptyArray()) {
    if (newText == null ||
        (newText.isBlank() && hideWhenBlank)) {
        isVisible = false
        relatedViews.forEach { it.isVisible = false }
    } else {
        this.text = newText
        isVisible = true
        relatedViews.forEach { it.isVisible = true }
    }
}

fun TextView.setLeftDrawable(drawable: Drawable?) {
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

fun TextView.clearDrawables() {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
}

fun TextView.updateTextSize(textSizeInSp : Float = 12f){
    if (Build.VERSION.SDK_INT >= 26) {
        this.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_NONE)
    }
    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeInSp)
}

