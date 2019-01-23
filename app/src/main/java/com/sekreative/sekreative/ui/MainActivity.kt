package com.sekreative.sekreative.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

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
        setSupportActionBar(bottom_app_bar)

        FeedFragment.newInstance().show()

        btn_logout.setOnClickListener {
            Prefs.clearAuthToken()
            startActivity<AuthActivity>()
            finish()
            return@setOnClickListener
        }

    }

    override fun onResume() {
        super.onResume()
        val token = Prefs.getAuthToken()
        if (token?.isBlank() == true) {
            startActivity<AuthActivity>()
            finish()
        }
    }

    /**
     * Custom functionality for onBackPressed
     */
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


    /**
     * Do not create a new instance of the supportFragmentTransaction for your fragment transactions in this activity.
     * Use this function instead to show a fragment onto screen.
     */
    private fun Fragment.show(tag: String = "") {
        val transaction = supportFragmentManager.beginTransaction()
        if (tag.isNotBlank()) {
            transaction.addToBackStack(tag)
        }
        transaction.replace(R.id.frame_main, this, tag)
        transaction.commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.profile -> ProfileFragment.newInstance().show(ProfileFragment.TAG)
        }
        return super.onOptionsItemSelected(item)
    }
}