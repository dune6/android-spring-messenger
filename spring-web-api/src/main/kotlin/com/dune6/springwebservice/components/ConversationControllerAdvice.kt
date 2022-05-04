package com.dune6.springwebservice.components

import com.dune6.springwebservice.constants.ErrorResponse
import com.dune6.springwebservice.exceptions.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {

    @ExceptionHandler
    fun conversationIdInvalidException(conversationIdInvalidException: ConversationIdInvalidException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }


}