package com.dune6.springwebservice.repositories

import com.dune6.springwebservice.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}