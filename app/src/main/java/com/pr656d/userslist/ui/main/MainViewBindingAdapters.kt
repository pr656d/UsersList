package com.pr656d.userslist.ui.main

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pr656d.userslist.model.User
import com.pr656d.userslist.ui.main.UserListAdapter

@BindingAdapter(value = ["userListItems", "userListViewModel"], requireAll = true)
fun userListItems(
    recyclerView: RecyclerView,
    list: List<User>?,
    userListViewModel: MainViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = UserListAdapter(userListViewModel)
    }

    if (list.isNullOrEmpty()) {
        recyclerView.isVisible = false
    } else {
        recyclerView.isVisible = true
        (recyclerView.adapter as UserListAdapter).submitList(list)
    }
}