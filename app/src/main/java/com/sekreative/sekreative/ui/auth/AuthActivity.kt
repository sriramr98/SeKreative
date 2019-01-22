package com.sekreative.sekreative.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.MainActivity
import com.sekreative.sekreative.utils.Prefs
import org.jetbrains.anko.startActivity

class AuthActivity: AppCompatActivity(), LoginFragment.LoginFragmentInteractionListener, OtpFragment.OtpFragmentInteractionListener {
    override fun onLogin(number: String) {
        OtpFragment.newInstance(number).show(OtpFragment.TAG)
    }

    override fun verifyOtp(otp: String) {
        Prefs.setAuthToken()
        startActivity<MainActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        LoginFragment.newInstance().show()
    }

    private fun Fragment.show(tag: String = "") {
        val transaction = supportFragmentManager.beginTransaction()
        if (tag.isNotBlank()) {
            transaction.addToBackStack(tag)
        }
        transaction.replace(R.id.frame_auth, this, tag)
        transaction.commitAllowingStateLoss()
    }
}