package com.pr656d.userslist.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import com.pr656d.userslist.R
import com.pr656d.userslist.data.static.users
import com.pr656d.userslist.model.User
import com.pr656d.userslist.ui.theme.UsersListTheme
import com.pr656d.userslist.ui.theme.typography
import com.pr656d.userslist.viewmodel.MainViewModel

@Composable
fun MainContent(viewModel: MainViewModel) {
    UsersListTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = ContextAmbient.current.getString(R.string.app_name))
                        }
                    )
                }
            ) {
                val users by viewModel.users.observeAsState()
                LoadUsers(users ?: emptyList())
            }
        }
    }
}

@Composable
fun LoadUsers(users: List<User>) {
    if (users.isNotEmpty()) {
        LazyColumnFor(
            modifier = Modifier.fillMaxSize(),
            items = users
        ) {
            UserItem(user = it)
        }
    } else {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val noUsers = createRef()

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(noUsers) {
                        centerTo(parent)
                    }
                ,
                text = ContextAmbient.current.getString(R.string.users_list_empty),
                style = typography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(
    name = "Users • Light",
    group = "Users List"
)
@Composable
fun MainContentPreviewLight() {
    UsersListTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = ContextAmbient.current.getString(R.string.app_name))
                        }
                    )
                }
            ) {
                LoadUsers(users)
            }
        }
    }
}

@Preview(
    name = "Users • Dark",
    group = "Users List"
)
@Composable
fun MainContentPreviewDark() {
    UsersListTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = ContextAmbient.current.getString(R.string.app_name))
                        }
                    )
                }
            ) {
                LoadUsers(users)
            }
        }
    }
}

@Preview(
    name = "No users • Light",
    group = "No users"
)
@Composable
fun MainContentNoUsersPreviewLight() {
    UsersListTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = ContextAmbient.current.getString(R.string.app_name))
                        }
                    )
                }
            ) {
                LoadUsers(emptyList())
            }
        }
    }
}

@Preview(
    name = "No users • Dark",
    group = "No users"
)
@Composable
fun MainContentNoUsersPreviewDark() {
    UsersListTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = ContextAmbient.current.getString(R.string.app_name))
                        }
                    )
                }
            ) {
                LoadUsers(emptyList())
            }
        }
    }
}
