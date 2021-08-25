package com.example.userdataapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alphauser.R
import com.example.userdataapp.Model.Data


class UserAdapters(private val context: Context, private var userdata: ArrayList<Data>, private var listener : OnItemClickListener) :
    RecyclerView.Adapter<UserAdapters.userViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        return userViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, null))
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val user: Data = userdata[position]
        Glide.with(context).load(user.avatar).into(holder.userImage)
        holder.username.text = user.first_name +" "+ user.last_name
        holder.userEmail.text = user.email
        holder.userID.text = user.id.toString()
    }

    override fun getItemCount(): Int = userdata.size

    fun setUserDataAdapter(userdata: ArrayList<Data>) {
        this.userdata = userdata
        notifyDataSetChanged()
    }

    inner class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val userImage: ImageView = itemView.findViewById(R.id.user_image)
        val username: TextView = itemView.findViewById(R.id.user_name)
        val userEmail: TextView = itemView.findViewById(R.id.user_Email)
        val userID: TextView = itemView.findViewById(R.id.userId)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = layoutPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(userdata.get(position))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(userdata: Data)
    }
}