package com.sekreative.sekreative.ui.auth


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.sekreative.sekreative.R
import com.sekreative.sekreative.utils.onTextChanged
import com.sekreative.sekreative.utils.text
import kotlinx.android.synthetic.main.fragment_otp.*

class OtpFragment : Fragment() {

    private var mOtpListener: OtpFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_verify.setOnClickListener {
            val otp = edt_otp.text()
            mOtpListener?.verifyOtp(otp)

            pb_otp.visibility = View.VISIBLE
            pb_otp.isIndeterminate = true
        }

        edt_otp.onTextChanged {
            layout_otp.error = null
        }
    }

    companion object {
        /**
         * Use this factory instance method to instantiate a new fragment
         */
        fun newInstance() = OtpFragment()

        const val TAG = "OtpFragment"
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OtpFragmentInteractionListener {
        fun verifyOtp(otp: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OtpFragmentInteractionListener) {
            mOtpListener = context
        } else {
            throw RuntimeException("$context must implement LoginFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        mOtpListener = null
        super.onDetach()
    }
}
