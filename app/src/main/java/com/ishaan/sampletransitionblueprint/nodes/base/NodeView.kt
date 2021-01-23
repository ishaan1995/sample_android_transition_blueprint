package com.ishaan.sampletransitionblueprint.nodes.base

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.children
import java.lang.IllegalStateException

abstract class NodeView(
    private val rootView: FrameLayout,
    private val layoutId: Int
) {
    private lateinit var nodeView: View
    private var childNodes: MutableList<NodeView> = mutableListOf()

    fun addToParent() {
        LayoutInflater.from(rootView.context).run {
            nodeView = inflate(layoutId, rootView, false)
            rootView.addView(nodeView)
            onAttach()
        }
    }

    fun removeFromParent() {
        rootView.removeView(nodeView)
        onDetach()
    }

    fun getView(): View? {
        return nodeView
    }

    open fun onAttach() {

    }

    open fun onDetach() {

    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    open fun handlBackPress(): Boolean {
        return true
    }

    fun addChildNode(nodeView: NodeView, nodeEvent: NodeEvent) {
        childNodes.add(nodeView)
    }
}