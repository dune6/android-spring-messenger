package com.dune6.springwebservice.components

import com.dune6.springwebservice.exceptions.UserDeactivatedException
import com.dune6.springwebservice.models.User
import com.dune6.springwebservice.repositories.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AccountValidityInterceptor(
    val userRepository: UserRepository
) : HandlerInterceptor {

    @Throws(UserDeactivatedException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal: Principal? = request.userPrincipal
        if (principal != null) {
            val user = userRepository.findByUsername(request.userPrincipal.name) as User
            if (user.accountStatus == "deactivated") {
                throw UserDeactivatedException("The account of this user has been deactivated")
            }
        }
        return super.preHandle(request, response, handler)
    }
}