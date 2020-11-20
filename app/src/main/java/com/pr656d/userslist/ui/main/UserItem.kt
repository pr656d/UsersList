package com.pr656d.userslist.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.pr656d.userslist.data.static.user
import com.pr656d.userslist.model.User
import com.pr656d.userslist.ui.components.Placeholder
import com.pr656d.userslist.ui.theme.UsersListTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun UserItem(user: User) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            UserImage(url = user.picture?.medium)
            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = user.name.toString(),
                    modifier = Modifier.wrapContentSize(Alignment.TopStart),
                    style = typography.body1
                )
                user.location?.address()?.also {
                    Text(
                        text = it,
                        modifier = Modifier.wrapContentSize(Alignment.TopStart).padding(top = 4.dp),
                        style = typography.subtitle1
                    )
                }
            }
        }
    }
}

@Composable
fun UserImage(url: String?) {
    Box(
        modifier = Modifier
            .preferredSize(100.dp)
            .clip(CircleShape)
    ) {
        url?.let {
            CoilImage(
                data = it,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                fadeIn = true,
                loading = { Placeholder() }
            )
        } ?: Placeholder()
    }
}

@Preview(
    name = "UserItem * Light"
)
@Composable
fun previewUserLight() {
    UsersListTheme(darkTheme = false) {
        UserItem(user)
    }
}

@Preview(
    name = "UserItem * Dark"
)
@Composable
fun previewUserDark() {
    UsersListTheme(darkTheme = true) {
        UserItem(user)
    }
}
