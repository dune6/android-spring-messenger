package com.dune6.springwebservice.services

import com.dune6.springwebservice.models.User

interface UserService {

    fun attemptRegistration(userDetails: User): User

    fun listUsers(currentUser: User): List<User>

    fun retrieveUserData(username: String): User?

    fun retrieveUserData(id: Long): User?

    fun usernameExists(username: String): Boolean

}