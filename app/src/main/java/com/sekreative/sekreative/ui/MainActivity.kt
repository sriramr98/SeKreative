package com.sekreative.sekreative.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity<AuthActivity>()
            finish()
            return@setOnClickListener
        }
    }

    override fun onResume() {
        super.onResume()
        if (mAuth.currentUser == null) {
            startActivity<AuthActivity>()
            finish()
            return
        }
    }
}
