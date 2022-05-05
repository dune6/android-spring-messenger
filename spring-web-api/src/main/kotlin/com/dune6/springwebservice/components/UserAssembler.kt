package com.dune6.springwebservice.components

import com.dune6.springwebservice.helpers.objects.UserListVO
import com.dune6.springwebservice.helpers.objects.UserVO
import com.dune6.springwebservice.models.User
import org.springframework.stereotype.Component

@Component
class UserAssembler {

    fun toUserVO(user: User): UserVO {
        return UserVO(user.id, user.username, user.phoneNumber, user.status, user.createdAt.toString())
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map { toUserVO(it) }
        return UserListVO(userVOList)
    }
}