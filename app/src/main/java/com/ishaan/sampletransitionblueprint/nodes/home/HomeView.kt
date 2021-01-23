package com.ishaan.sampletransitionblueprint.nodes.home

import android.widget.FrameLayout
import android.widget.ImageView
import com.ishaan.sampletransitionblueprint.R
import com.ishaan.sampletransitionblueprint.nodes.base.NodeView
import com.ishaan.sampletransitionblueprint.nodes.search.SearchView

class HomeView(
    private val rootView: FrameLayout,
    private val layoutId: Int = R.layout.node_home
): NodeView(rootView, layoutId) {

    private var searchChildNode: NodeView? = null

    override fun onAttach() {
        getView()?.run {
            findViewById<ImageView>(R.id.search).setOnClickListener {
                openSearchView()
            }
        }
    }

    private fun openSearchView() {
        if (searchChildNode == null) {
            searchChildNode = SearchView(rootView = rootView)
        }
        searchChildNode?.addToParent()
    }

    override fun handlBackPress(): Boolean {
        return searchChildNode?.handlBackPress() ?: true
    }

    override fun onDetach() {

    }
}