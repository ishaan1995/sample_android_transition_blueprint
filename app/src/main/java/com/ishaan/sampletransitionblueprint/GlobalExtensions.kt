package com.ishaan.sampletransitionblueprint

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun View.fadeInAnimator(
    duration: Long = 250,
    customInterpolator: TimeInterpolator? = null,
    startDelay: Long? = null
): Animator {
    val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
    customInterpolator?.let {
        animator.interpolator = customInterpolator
    }
    animator.duration = duration
    startDelay?.let {
        animator.startDelay = it
    }
    animator.addUpdateListener {
        this.alpha = it.animatedValue as Float
    }
    return animator
}

fun View.fadeOutAnimator(
    duration: Long = 250,
    customInterpolator: TimeInterpolator? = null,
    startDelay: Long? = null
): Animator {
    val animator = ValueAnimator.ofFloat(1.0f, 0.0f)
    customInterpolator?.let {
        animator.interpolator = customInterpolator
    }
    startDelay?.let {
        animator.startDelay = it
    }
    animator.duration = duration
    animator.addUpdateListener {
        this.alpha = it.animatedValue as Float
    }
    return animator
}

fun View.setWidth(width: Int) {
    val params = this.layoutParams
    params.width = width
    this.layoutParams = params
}

fun EditText.showKeyboard() {
    requestFocus()
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.showSoftInput(
        this, 0
    )
}

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()