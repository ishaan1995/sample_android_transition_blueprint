package com.ishaan.sampletransitionblueprint.nodes.search

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import com.ishaan.sampletransitionblueprint.R
import com.ishaan.sampletransitionblueprint.nodes.base.NodeView

class SearchView(
    private val rootView: FrameLayout,
    private val layoutId: Int = R.layout.node_search
): NodeView(rootView, layoutId) {

    override fun onAttach() {
        getView()?.run {
            val fadeInAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
            fadeInAnimator.duration = 250
            fadeInAnimator.addUpdateListener {
                this.alpha = it.animatedValue as Float
            }
            fadeInAnimator.start()
        }
    }

    override fun handlBackPress(): Boolean {
        planForExit()
        return false
    }

    private fun planForExit() {
        getView()?.run {
            val fadeOutAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
            fadeOutAnimator.duration = 250
            fadeOutAnimator.addUpdateListener {
                this.alpha = it.animatedValue as Float
            }
            fadeOutAnimator.doOnEnd {
                removeFromParent()
            }
            fadeOutAnimator.start()
        }
        // notify one node up that this node wants to be detached.
    }

    override fun onDetach() {

    }
}