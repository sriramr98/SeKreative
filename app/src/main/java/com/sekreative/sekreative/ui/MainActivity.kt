package com.sekreative.sekreative.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.addpost.AddPostFragment
import com.sekreative.sekreative.ui.auth.AuthActivity
import com.sekreative.sekreative.ui.feed.FeedFragment
import com.sekreative.sekreative.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var currentFragment = FeedFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FeedFragment.newInstance().show()

        btn_logout.setOnClickListener {

            finish()
            return@setOnClickListener
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when {
                it.itemId == R.id.action_feed -> {
                    if (currentFragment != FeedFragment.TAG) {
                        FeedFragment.newInstance().show()
                        currentFragment = FeedFragment.TAG
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                it.itemId == R.id.action_add_post -> {
                    if (currentFragment != AddPostFragment.TAG) {
                        AddPostFragment.newInstance().show()
                        currentFragment = AddPostFragment.TAG
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                it.itemId == R.id.action_profile -> {
                    if (currentFragment != ProfileFragment.TAG) {
                        ProfileFragment.newInstance().show()
                        currentFragment = ProfileFragment.TAG
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

    }

    override fun onBackPressed() {
        when (currentFragment) {
            FeedFragment.TAG -> finish()
            AddPostFragment.TAG, ProfileFragment.TAG -> {
                FeedFragment.newInstance().show()
                currentFragment = FeedFragment.TAG
            }
            else -> super.onBackPressed()
        }
    }

    private fun Fragment.show(tag: String = "") {
        val transaction = supportFragmentManager.beginTransaction()
        if (tag.isNotBlank()) {
            transaction.addToBackStack(tag)
        }
        transaction.replace(R.id.frame_main, this, tag)
        transaction.commit()
    }
}
