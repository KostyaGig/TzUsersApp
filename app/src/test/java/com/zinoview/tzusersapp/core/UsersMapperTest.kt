package com.zinoview.tzusersapp.core

/**
 * Test for [com.zinoview.tzusersapp.core.Abstract.UsersMapper]
 */

import org.junit.Assert.*
import org.junit.Test

class UsersMapperTest {

    @Test
    fun test_success_map_data_users_to_domain_users() {
        val dataUsers = TestDataUsers.Success(
            listOf(
                User.Base(
                1,"testuser@mail.ru","Kostya","Brob","avatar.jpg"
                ),
                User.Base(
                    2,"test2user@mail.ru","Minor","Fromik","avatar2.jpg"
                )
            )
        )

        val mapperTestDataUsersToDomainUsers = MapperTestDataUsersToDomainUsers()
        val domainUsers = dataUsers.map(mapperTestDataUsersToDomainUsers)

        val expected = TestDomainUsers.Success(
            listOf(
                User.Base(
                    1,"testuser@mail.ru","Kostya","Brob","avatar.jpg"
                ),
                User.Base(
                    2,"test2user@mail.ru","Minor","Fromik","avatar2.jpg"
                )
            )
        )

        assertEquals(expected,domainUsers)
        assertTrue(domainUsers is TestDomainUsers.Success)
    }

    @Test
    fun test_failure_map_data_users_to_domain_users() {
        val dataUsers = TestDataUsers.Failure("No connection")

        val mapperTestDataUsersToDomainUsers = MapperTestDataUsersToDomainUsers()
        val domainUsers = dataUsers.map(mapperTestDataUsersToDomainUsers)

        val expected = TestDomainUsers.Failure("No connection")

        assertEquals(expected,domainUsers)
        assertTrue(domainUsers is TestDomainUsers.Failure)
    }

    private sealed class TestDataUsers : Abstract.Users {
        data class Success(
            private val users: List<User>
        ) : TestDataUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                = mapper.map(users)
        }

        data class Failure(
            private val message: String
        ) : TestDataUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                = mapper.map(message)
        }
    }

    private sealed class TestDomainUsers : Abstract.Users {

        data class Success(
            private val users: List<User>
        ) : TestDomainUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                    = mapper.map(users)
        }

        data class Failure(
            private val message: String
        ) : TestDomainUsers() {

            override fun <T> map(mapper: Abstract.UsersMapper<T>): T
                    = mapper.map(message)
        }
    }

    private inner class MapperTestDataUsersToDomainUsers : Abstract.UsersMapper<TestDomainUsers> {

        override fun map(users: List<User>): TestDomainUsers
            = TestDomainUsers.Success(users)

        override fun map(message: String): TestDomainUsers
            = TestDomainUsers.Failure(message)
    }
}