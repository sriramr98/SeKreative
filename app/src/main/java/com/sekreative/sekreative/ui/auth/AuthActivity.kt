package com.sekreative.sekreative.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.MainActivity
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class AuthActivity : AppCompatActivity(), LoginFragment.LoginFragmentInteractionListener,
    OtpFragment.OtpFragmentInteractionListener {

    private var mVerificationId: String = ""

    private val mAuth = FirebaseAuth.getInstance()
    private val mAuthCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential?) {
            signInUser(credential)
        }

        override fun onVerificationFailed(e: FirebaseException?) {
            Log.e("AuthActivity", e?.message)
            MaterialDialog(this@AuthActivity).show {
                title(res = R.string.error_login_title)
                message(res = R.string.error_login_message)
                positiveButton(res = R.string.button_ok)
            }
        }

        override fun onCodeSent(verificationId: String?, token: PhoneAuthProvider.ForceResendingToken?) {
            OtpFragment.newInstance().show(OtpFragment.TAG)
            mVerificationId = verificationId ?: ""
        }
    }

    private fun signInUser(credential: PhoneAuthCredential?) {
        mAuth.signInWithCredential(credential!!)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity<MainActivity>()
                    finish()
                } else {
                    val message = if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        "Invalid OTP. Please try again"
                    } else {
                        "Unable to sign in. Please try again."
                    }
                    MaterialDialog(this)
                        .show {
                            title(text = "Error logging in")
                            message(text = message)
                            positiveButton(text = "OK")
                        }
                }
            }
    }

    override fun onLogin(number: String) {
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(
                "+91$number",
                60,
                TimeUnit.SECONDS,
                this,
                mAuthCallbacks
            )
    }

    override fun verifyOtp(otp: String) {
        val credentials = PhoneAuthProvider.getCredential(mVerificationId, otp)
        signInUser(credentials)
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
        transaction.replace(R.id.frame_auth, this)
        transaction.commit()
    }

}
