package com.dune6.springwebservice.services

import com.dune6.springwebservice.models.Message
import com.dune6.springwebservice.models.User

interface MessageService {
    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}