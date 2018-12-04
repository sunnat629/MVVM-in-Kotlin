package com.sunnat629.mvvmroomexample.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sunnat629.mvvmroomexample.R
import com.sunnat629.mvvmroomexample.model.Users
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import java.util.*

class UserListAdapter(context: Context) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)!!
    private var mUsers: List<Users> = Collections.emptyList()


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rowItem: TextView = itemView.rowItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UserViewHolder {
        val itemView: View = mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        holder.rowItem.text = mUsers[position].toString()
    }

    fun addUser(users: List<Users>){
        mUsers = users
        notifyDataSetChanged()
    }
}