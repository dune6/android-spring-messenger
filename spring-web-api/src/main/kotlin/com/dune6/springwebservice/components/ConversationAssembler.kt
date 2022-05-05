package com.dune6.springwebservice.components

import com.dune6.springwebservice.helpers.objects.ConversationListVO
import com.dune6.springwebservice.helpers.objects.ConversationVO
import com.dune6.springwebservice.helpers.objects.MessageVO
import com.dune6.springwebservice.models.Conversation
import com.dune6.springwebservice.services.ConversationServiceImpl
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(
    val conversationServiceImpl: ConversationServiceImpl,
    val messageAssembler: MessageAssembler
) {

    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages?.mapTo(conversationMessages) {
            messageAssembler.toMessageVO(it)
        }
        return ConversationVO(
            conversation.id,
            conversationServiceImpl.nameSecondParty(conversation, userId),
            conversationMessages
        )
    }

    fun toConversationListVO(
        conversations: ArrayList<Conversation>,
        userId: Long
    ): ConversationListVO {
        val conversationVOList = conversations.map {
            toConversationVO(it, userId)
        }
        return ConversationListVO(conversationVOList)
    }


}