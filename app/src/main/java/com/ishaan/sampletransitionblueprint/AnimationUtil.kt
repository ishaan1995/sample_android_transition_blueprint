package com.ishaan.sampletransitionblueprint

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.animation.doOnEnd
import androidx.core.view.animation.PathInterpolatorCompat


class AnimationUtil(private val rootView: FrameLayout) {
    fun animateForSearchOpenOnHome(onComplete: () -> Unit) {
        val rootContent: View = rootView.findViewById(R.id.v_content)
        val maxWidthTranslation = rootView.width.toFloat() / 4
        val animator = ObjectAnimator.ofFloat(
            rootContent,
            View.TRANSLATION_X,
            0f,
            -1 * maxWidthTranslation
        )
        animator.duration = 500
        animator.interpolator = PathInterpolatorCompat.create(0.34f, 1.0f, 0.26f, 0.99f)
        animator.doOnEnd {
            onComplete()
        }
        animator.start()
    }

    fun animateForSearchOpening(onComplete: () -> Unit) {
        val animator = rootView.findViewById<LinearLayout>(R.id.node_search).fadeInAnimator(
            duration = 100L,
            startDelay = 50L,
            customInterpolator = PathInterpolatorCompat.create(0.30f, 0.28f, 0.61f, 1.0f)
        )
        val searchContentAnimator = rootView.findViewById<LinearLayout>(R.id.ll_search_content).fadeInAnimator(
            duration = 100L,
            customInterpolator = PathInterpolatorCompat.create(0.30f, 0.28f, 0.61f, 1.0f)
        )
        val searchInputEt = rootView.findViewById<EditText>(R.id.et_search_input)
        val searchInputRl = rootView.findViewById<RelativeLayout>(R.id.rl_search_input)
        val searchInputFadeAnimator = searchInputEt.fadeInAnimator(
            duration = 200L,
            startDelay = 250L,
            customInterpolator = PathInterpolatorCompat.create(0.30f, 0.28f, 0.61f, 1.0f)
        )

        val finalWidth: Int = rootView.findViewById<Toolbar>(R.id.toolbar_search).width - 16.toPx()
        val initialWidth: Int = 1.toPx()
        val searchBarWidthAnimator = ValueAnimator.ofInt(initialWidth, finalWidth).also { searchBarWidthAnimator ->
            searchBarWidthAnimator.duration = 500L
            searchBarWidthAnimator.interpolator = PathInterpolatorCompat.create(0.34f, 1.0f, 0.26f, 0.99f)
            searchBarWidthAnimator.addUpdateListener {
                searchInputRl.setWidth(it.animatedValue as Int)
            }

            searchBarWidthAnimator.doOnEnd {
                onComplete()
            }
        }
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(searchContentAnimator, searchBarWidthAnimator, animator, searchInputFadeAnimator)
        animatorSet.start()
    }

    fun animateForSearchClosing(onComplete: () -> Unit) {

        val searchContentAnimator = rootView.findViewById<LinearLayout>(R.id.ll_search_content).fadeOutAnimator(
            duration = 100L,
            customInterpolator = PathInterpolatorCompat.create(0.30f, 0.28f, 0.61f, 1.0f)
        )

        val searchToolbarAnimator = rootView.findViewById<Toolbar>(R.id.toolbar_search).fadeOutAnimator(
            duration = 500,
            customInterpolator = PathInterpolatorCompat.create(0.30f, 0.28f, 0.61f, 1.0f)
        )

        val searchInputRl = rootView.findViewById<RelativeLayout>(R.id.rl_search_input)
        val initialWidth: Int = rootView.findViewById<Toolbar>(R.id.toolbar_search).width - 16.toPx()
        val finalWidth: Int = initialWidth / 4
        val searchBarWidthAnimator = ValueAnimator.ofInt(initialWidth, finalWidth).also { searchBarWidthAnimator ->
            searchBarWidthAnimator.interpolator = PathInterpolatorCompat.create(0.34f, 1.0f, 0.26f, 0.99f)
            searchBarWidthAnimator.duration = 500L
            searchBarWidthAnimator.addUpdateListener {
                searchInputRl.post {
                    searchInputRl.alpha = 1 - it.animatedFraction
                    searchInputRl.setWidth(it.animatedValue as Int)
                }
            }

            searchBarWidthAnimator.doOnEnd {
                onComplete()
            }
        }
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(searchContentAnimator, searchToolbarAnimator, searchBarWidthAnimator)
        animatorSet.start()
    }

    fun animateForSearchClosedOnHome(onComplete: () -> Unit) {
        val rootContent: View = rootView.findViewById(R.id.v_content)
        val maxWidthTranslation = rootView.width.toFloat() / 4
        val animator = ObjectAnimator.ofFloat(
            rootContent,
            View.TRANSLATION_X,
            -1 * maxWidthTranslation,
            0f
        )
        animator.duration = 500
        animator.interpolator = PathInterpolatorCompat.create(0.34f, 1.0f, 0.26f, 0.99f)
        animator.doOnEnd {
            onComplete()
        }
        animator.start()
    }
}