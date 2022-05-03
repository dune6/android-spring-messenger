package com.dune6.springwebservice.services

import com.dune6.springwebservice.exceptions.MessageEmptyException
import com.dune6.springwebservice.exceptions.MessageRecipientInvalidException
import com.dune6.springwebservice.models.Conversation
import com.dune6.springwebservice.models.Message
import com.dune6.springwebservice.models.User
import com.dune6.springwebservice.repositories.ConversationRepository
import com.dune6.springwebservice.repositories.MessageRepository
import com.dune6.springwebservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
    val repository: MessageRepository, val conversationRepository: ConversationRepository,
    val conversationService: ConversationService, val userRepository: UserRepository
) : MessageService {

    @Throws(MessageEmptyException::class, MessageRecipientInvalidException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)

        if (optional.isPresent) {
            val recipient = optional.get()

            if (messageText.isNotEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message =
                    Message(sender = sender, recipient = recipient, body = messageText, conversation = conversation)
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id $recipientId is invalid")
        }
        throw MessageEmptyException()
    }

}