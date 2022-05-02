package com.dune6.springwebservice.listeners

import com.dune6.springwebservice.models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class UserListener {

    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        // hash passwords before save in DB
        user.password = BCryptPasswordEncoder().encode(user.password)
    }

}