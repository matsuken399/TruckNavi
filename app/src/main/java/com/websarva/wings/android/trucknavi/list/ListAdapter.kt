package com.websarva.wings.android.trucknavi.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.trucknavi.R
import com.websarva.wings.android.trucknavi.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.date_txt.text = currentItem.date.toString()
        holder.itemView.course_txt.text = currentItem.course.toString()
        holder.itemView.no_txt.text = currentItem.no.toString()
        holder.itemView.name_txt.text = currentItem.name
        holder.itemView.lat_txt.text = currentItem.lat.toString()
        holder.itemView.lng_txt.text = currentItem.lng.toString()

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}