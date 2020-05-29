package com.pr656d.userslist.data.remote

import com.pr656d.userslist.data.user.datasource.UserEndPoints
import com.pr656d.userslist.data.user.datasource.response.UserResponse
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * This should be replaced by separate service for every data source.
 * ex. Place UserDataSource inside user.datasource package.
 */
@Singleton
interface NetworkService {

    /**
     * Fetch the users and
     * @return [UserResponse].
     */
    @GET(UserEndPoints.USERS)
    suspend fun getUsers(): UserResponse

}