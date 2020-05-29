package com.pr656d.userslist.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pr656d.userslist.fakes.FakeUserRepository
import com.pr656d.userslist.model.User
import com.pr656d.userslist.rule.MainCoroutineRule
import com.pr656d.userslist.rule.runBlockingTest
import com.pr656d.userslist.util.LiveDataTestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.hamcrest.Matchers.equalTo as isEqualTo

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    /**
     * Verify that list is loaded.
     */
    @Test
    fun verifyListIsLoaded() = coroutineRule.runBlockingTest {
        val userRepository = object : FakeUserRepository() {
            override fun getAll(): Flow<List<User>> {
                // Return test data instead of empty list.
                // For now to make user test data is time consuming.
                return flowOf(emptyList())
            }
        }

        val viewModel = MainViewModel(userRepository)

        val list = LiveDataTestUtil.getValue(viewModel.users)

        assertThat(0, isEqualTo(list?.size))
    }
}