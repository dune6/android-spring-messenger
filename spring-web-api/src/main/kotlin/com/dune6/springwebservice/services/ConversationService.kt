package com.dune6.springwebservice.services

import com.dune6.springwebservice.models.Conversation
import com.dune6.springwebservice.models.User

interface ConversationService {

    fun createConversation(userA: User, userB: User): Conversation
    fun conversationExists(userA: User, userB: User): Boolean
    fun getConversation(userA: User, userB: User): Conversation?
    fun retrieveThread(conversationId: Long): Conversation
    fun listUserConversations(userId: Long): List<Conversation>
    fun nameSecondParty(conversation: Conversation, userId: Long): String
}