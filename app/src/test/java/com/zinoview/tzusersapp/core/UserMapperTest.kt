package com.zinoview.tzusersapp.core

import org.junit.Test

/**
 * Test for [com.zinoview.tzusersapp.core.Abstract.UserMapper]
 */

import org.junit.Assert.*

class UserMapperTest {

    @Test
    fun test_map_user_data_to_domain() {
        val mapperDataToDomainTestUser = MapperDataToDomainTestUser()
        val dataUser = TestDataUser(1,"testuser@mail.ru","Kostya","Brob","avatar.jpg")

        val expected = TestDomainUser(1,"testuser@mail.ru","Kostya","Brob","avatar.jpg")
        val domainUser = dataUser.map(mapperDataToDomainTestUser)

        assertEquals(expected,domainUser)
    }

    private data class TestDataUser(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : Abstract.User {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
            = mapper.map(id, email, firstName, lastName, avatar)
    }

    private data class TestDomainUser(
        private val id: Int,
        private val email: String,
        private val firstName: String,
        private val lastName: String,
        private val avatar: String
    ) : Abstract.User {

        override fun <T> map(mapper: Abstract.UserMapper<T>): T
                = mapper.map(id, email, firstName, lastName, avatar)
    }

    private inner class MapperDataToDomainTestUser : Abstract.UserMapper<TestDomainUser> {

        override fun map(
            id: Int,
            email: String,
            firstName: String,
            lastName: String,
            avatar: String
        ): TestDomainUser
            = TestDomainUser(id, email, firstName, lastName, avatar)
    }
}