package com.ishaan.sampletransitionblueprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDelegate
import com.ishaan.sampletransitionblueprint.nodes.home.HomeView

class MainActivity : AppCompatActivity() {

    private val rootView: FrameLayout by lazy {
        findViewById<FrameLayout>(R.id.root)
    }

    private val homeView: HomeView by lazy {
        HomeView(rootView = rootView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        init()
    }

    private fun init() {
        homeView.addToParent()
    }

    override fun onBackPressed() {
        if (homeView.handlBackPress()) {
            super.onBackPressed()
        }
    }
}