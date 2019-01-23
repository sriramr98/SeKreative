package com.sekreative.sekreative.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sekreative.sekreative.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileAdapter(context: Context): RecyclerView.Adapter<ProfileAdapter.MyViewHolder>() {

    private var mContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.profile_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivUser: CircleImageView = itemView.findViewById(R.id.iv_circle_profile)
        var ivPost: AppCompatImageView = itemView.findViewById(R.id.iv_post)
        var ivLike: AppCompatImageView = itemView.findViewById(R.id.iv_like)
        var ivComment: AppCompatImageView = itemView.findViewById(R.id.iv_comment)
        var tvName: AppCompatTextView = itemView.findViewById(R.id.tv_name)
        var tvTime: AppCompatTextView = itemView.findViewById(R.id.tv_time)
        var tvLike: AppCompatTextView = itemView.findViewById(R.id.tv_like)
        var tvComment: AppCompatTextView = itemView.findViewById(R.id.tv_comment)

    }
}