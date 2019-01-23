package com.sekreative.sekreative.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sekreative.sekreative.R
import com.sekreative.sekreative.ui.adapters.ProfileAdapter
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        const val TAG = "ProfileFragment"
        fun newInstance() = ProfileFragment()
    }

    private lateinit var mAdapter: ProfileAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = linearLayoutManager
        mAdapter = ProfileAdapter(activity!!.applicationContext)
        recycler_view.adapter = mAdapter
        // TODO: Use the ViewModel
    }

}
