package com.dune6.springwebservice.controllers

import com.dune6.springwebservice.components.ConversationAssembler
import com.dune6.springwebservice.helpers.objects.ConversationListVO
import com.dune6.springwebservice.helpers.objects.ConversationVO
import com.dune6.springwebservice.models.User
import com.dune6.springwebservice.repositories.UserRepository
import com.dune6.springwebservice.services.ConversationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/conversations")
class ConversationController(
    val conversationServiceImpl: ConversationServiceImpl,
    val conversationAssembler: ConversationAssembler,
    val userRepository: UserRepository
) {
    @GetMapping
    fun list(request: HttpServletRequest): ResponseEntity<ConversationListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversations = conversationServiceImpl.listUserConversations(user.id)
        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.id))
    }

    @GetMapping
    @RequestMapping("/{conversation_id}")
    fun show(
        @PathVariable(name = "conversation_id") conversationId: Long,
        request: HttpServletRequest
    ): ResponseEntity<ConversationVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val conversationThread = conversationServiceImpl.retrieveThread(conversationId)
        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.id))
    }
}