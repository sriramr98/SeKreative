package com.sekreative.sekreative.ui.bottomnavigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.adapters.OnlineUsersAdapter
import com.sekreative.sekreative.ui.adapters.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_bottom_navigation_drawer.*
import kotlinx.android.synthetic.main.profile_fragment.*

/**
 * This class displays the list of all the Online Users.
 *
 */
class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "BottomNavigationDrawerFragment"
        /**
         * Use this factory method to create a new instance of this fragment
         * @return A new instance of fragment BottomNavigationDrawerFragment.
         */
        fun newInstance() = BottomNavigationDrawerFragment()
    }

    private lateinit var mAdapter: OnlineUsersAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation_drawer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_close.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        linearLayoutManager = LinearLayoutManager(activity)
        users_recycler_view.layoutManager = linearLayoutManager
        mAdapter = OnlineUsersAdapter(context!!)
        users_recycler_view.adapter = mAdapter

    }


}
