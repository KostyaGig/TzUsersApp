package com.zinoview.tzusersapp.data

import com.zinoview.tzusersapp.core.BaseUser
import com.zinoview.tzusersapp.data.cache.CacheDataSource
import com.zinoview.tzusersapp.data.cloud.CloudDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Test for [com.zinoview.tzusersapp.data.UsersRepository.Test]
 */

class UsersRepositoryTest {

    private lateinit var usersRepository: UsersRepository

    @Before
    fun setUp() {
        usersRepository = UsersRepository.Test(
            CacheDataSource.Test(),
            CloudDataSource.Test()
        )
    }

    @Test
    fun test_success_fetching_users() = runBlocking {
        val expected = flow {
            emit(
                DataUsers.Success(
                    listOf(
                        BaseUser.Test(
                            1,"testuser@mail.ru","Kostya","Brob","avatar.jpg"
                        ),
                        BaseUser.Test(
                            2,"test2user@mail.ru","Minor","Fromik","avatar2.jpg"
                        ),
                        BaseUser.Test(
                            3,"test3user@mail.ru","Feodora","Mint","avatar3.jpg"
                        )
                    )
                )
            )
        }.first()

        usersRepository.users()
        val actual = usersRepository.users().first()
        assertEquals(expected, actual)
    }

    @Test
    fun test_success_fetching_users_from_cache() = runBlocking {
        val expected = flow {
            emit(
                DataUsers.Cache(
                    listOf(
                        BaseUser.Test(
                            1,"testuser@mail.ru","Kostya","Brob","avatar.jpg"
                        ),
                        BaseUser.Test(
                            2,"test2user@mail.ru","Minor","Fromik","avatar2.jpg"
                        ),
                        BaseUser.Test(
                            3,"test3user@mail.ru","Feodora","Mint","avatar3.jpg"
                        )
                    )
                )
            )
        }.first()
        usersRepository.users()
        usersRepository.users()
        val actual = usersRepository.users().first()
        assertEquals(expected, actual)
    }

    @Test
    fun test_failure_fetching_users() = runBlocking {
        val expected = flow {
            emit(
                DataUsers.Failure(
                    "No connection"
                )
            )
        }.first()

        val actual = usersRepository.users().first()
        assertEquals(expected, actual)
    }
}