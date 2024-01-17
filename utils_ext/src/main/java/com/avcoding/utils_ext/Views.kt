package com.avcoding.utils_ext

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.ShapeAppearanceModel


fun View.withTypedArray(
    set: AttributeSet?,
    @StyleableRes attrs: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    action: TypedArray.() -> Unit
) {
    val typedArray = context.theme.obtainStyledAttributes(
        set, attrs, defStyleAttr, defStyleRes
    )
    action(typedArray)
    typedArray.recycle()
}

@ColorInt
fun View.resolveColor(@ColorRes colorRes: Int): Int =
    ResourcesCompat.getColor(this.context.resources, colorRes, null)

fun View.animateAlpha(visibility: Int, delay: Long = 200) {
    val alpha = when (visibility) {
        View.GONE, View.INVISIBLE -> 0f
        View.VISIBLE -> 1f
        else -> 1f
    }
    animate().apply {
        duration = delay
        alpha(alpha)
        withEndAction {
            setVisibility(visibility)
        }
    }
}


fun View.hide(){
    this.visibility = View.GONE
}
fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.show(){
    this.visibility = View.VISIBLE
}


fun MaterialCardView.upperCornerRadius(value: Float = 45f) {
    val shapeAppearanceModel: ShapeAppearanceModel.Builder = ShapeAppearanceModel().toBuilder()
    shapeAppearanceModel.setBottomRightCorner(
        CornerFamily.ROUNDED,
        CornerSize { return@CornerSize value })
    this.shapeAppearanceModel = shapeAppearanceModel.build()
}
