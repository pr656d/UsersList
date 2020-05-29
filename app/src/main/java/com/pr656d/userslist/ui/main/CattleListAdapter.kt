package com.pr656d.userslist.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pr656d.userslist.databinding.ItemUserBinding
import com.pr656d.userslist.model.User

class UserListAdapter(
    private val userListViewModel: MainViewModel
): ListAdapter<User, UserListViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            userListViewModel
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserListViewHolder(
    private val binding: ItemUserBinding,
    private val userListViewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: User) {
        binding.user = data
        binding.executePendingBindings()
    }
}

object UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.primaryKey == newItem.primaryKey

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}