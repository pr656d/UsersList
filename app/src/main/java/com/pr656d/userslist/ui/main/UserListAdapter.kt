package com.pr656d.userslist.ui.main

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pr656d.userslist.R
import com.pr656d.userslist.model.User
import com.pr656d.userslist.utils.CircularOutlineProvider
import com.squareup.picasso.Picasso

class UserListAdapter : ListAdapter<User, UserListViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder =
        UserListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserListViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    private val profileImage by lazy { view.findViewById<ImageView>(R.id.profileImage) }
    private val name by lazy { view.findViewById<TextView>(R.id.name) }
    private val address by lazy { view.findViewById<TextView>(R.id.address) }

    fun bind(data: User) {
        name.text = data.name.toString()
        address.text = data.location?.address()
        profileImage.apply {
            Picasso.get()
                .load(data.picture?.medium)
                .placeholder(R.drawable.image_placeholder)
                .into(this)
            clipToOutline = true
            outlineProvider = CircularOutlineProvider
        }
    }
}

object UserDiff : DiffUtil.ItemCallback<User>() {
    /**
     * As data is reloaded every time app launches comparing [User.primaryKey] will be always different.
     *
     * Instead compare [User.login.uuid] which is returned from the server.
     */
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.login!!.uuid == newItem.login!!.uuid

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}