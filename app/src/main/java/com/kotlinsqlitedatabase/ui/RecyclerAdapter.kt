package com.kotlinsqlitedatabase.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinsqlitedatabase.R
import com.kotlinsqlitedatabase.datamodel.UserModel
import com.kotlinsqlitedatabase.ktinterface.RecyclerItemClickListener
import kotlinx.android.synthetic.main.recycler_items.view.*

class RecyclerAdapter(
    val context: Context,
    val userList: ArrayList<UserModel>,
    val onItemClick: RecyclerItemClickListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_items, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.userName?.text = userList.get(position).name
        holder?.userAddress?.text = userList.get(position).address
        holder?.userPhone?.text = "Mo. " + userList.get(position).phone.toString()

        holder?.card_click.setOnClickListener {
            onItemClick.onItemClick(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userName = view.userName
        val userAddress = view.userAddress
        val userPhone = view.userPhone
        val card_click = view.card_click
    }
}