package com.ishaan.sampletransitionblueprint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.children
import androidx.core.view.doOnPreDraw


class MainActivity : AppCompatActivity() {

    private val rootView by lazy {
        findViewById<FrameLayout>(R.id.root)
    }

    private val searchIcon by lazy {
        findViewById<ImageView>(R.id.search)
    }

    private val animationUtil by lazy {
        AnimationUtil(rootView)
    }

    private var searchView: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        init()
    }

    private fun init() {
        searchIcon.setOnClickListener {
            showSearchView()
        }
    }

    private fun showSearchView() {
        animationUtil.animateForSearchOpenOnHome { }

        searchView = LayoutInflater.from(rootView.context).inflate(
            R.layout.node_search,
            rootView,
            false
        ) as LinearLayout

        searchView?.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View?) {}

            override fun onViewAttachedToWindow(v: View?) {
                searchView?.doOnPreDraw {
                    onSearchAddedToWindow()
                }
            }
        })

        rootView.addView(searchView)
    }

    private fun onSearchAddedToWindow() {
        animationUtil.animateForSearchOpening {
            Toast.makeText(this, "Opening animation complete!", Toast.LENGTH_SHORT).show()
            onSearchInit()
        }
    }

    private fun hideSearchView() {
        val searchEt = searchView?.findViewById<EditText>(R.id.et_search_input)
        searchEt?.hideKeyboard()
        animationUtil.animateForSearchClosedOnHome {
            rootView.removeView(searchView)
            Toast.makeText(this, "Closing animation complete!", Toast.LENGTH_SHORT).show()
        }
        animationUtil.animateForSearchClosing {
            searchView = null
        }
    }

    private fun onSearchInit() {
        val searchEt = searchView?.findViewById<EditText>(R.id.et_search_input)
        searchEt?.showKeyboard()

        val searchBackIcon = findViewById<ImageView>(R.id.iv_back)
        searchBackIcon.setOnClickListener {
            onBackPressed()
        }
    }

    private fun isSearchVisible(): Boolean {
        if (searchView != null) {
            return rootView.children.contains(searchView!!)
        }
        return false
    }

    override fun onBackPressed() {
        if (isSearchVisible()) {
            hideSearchView()
        } else {
            super.onBackPressed()
        }
    }
}