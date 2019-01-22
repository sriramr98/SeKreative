package com.sekreative.sekreative.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.addpost.AddPostFragment
import com.sekreative.sekreative.ui.auth.AuthActivity
import com.sekreative.sekreative.ui.feed.FeedFragment
import com.sekreative.sekreative.ui.profile.ProfileFragment
import com.sekreative.sekreative.utils.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var currentFragment = FeedFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}