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
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private var listener: LoginFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement LoginFragmentInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            val number = edt_number.text()

            if (number.isBlank()) {
                layout_number.error = getString(R.string.error_number_empty)
                return@setOnClickListener
            }

            if (number.length != 10) {
                layout_number.error = getString(R.string.error_number_length)
                return@setOnClickListener
            }

            pb_login.visibility = View.VISIBLE
            pb_login.isIndeterminate = true

            listener?.onLogin(number)

        }

        edt_number.onTextChanged {
            layout_number.error = null
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface LoginFragmentInteractionListener {
        fun onLogin(number: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}
