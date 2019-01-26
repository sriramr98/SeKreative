package com.sekreative.sekreative.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sekreative.sekreative.R

/**
 * This class define the Adapter for the list of Online Users.
 */
class OnlineUsersAdapter(context: Context): RecyclerView.Adapter<OnlineUsersAdapter.MyViewHolder>() {

    private var mContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.online_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = 20

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // TODO: Set data on all the views.
    }

    /**
     * This class describes an item view and metadata about its place within the RecyclerView.
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: find the reference of the views in this class
    }
}