package com.dune6.springwebservice.components

import com.dune6.springwebservice.helpers.objects.MessageVO
import com.dune6.springwebservice.models.Message
import org.springframework.stereotype.Component

@Component
class MessageAssembler {
    fun toMessageVO(message: Message): MessageVO {
        return MessageVO(
            message.id, message.sender?.id,
            message.recipient?.id, message.conversation?.id,
            message.body, message.createdAt.toString()
        )
    }
}
