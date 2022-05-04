package com.dune6.springwebservice.components

import com.dune6.springwebservice.constants.ErrorResponse
import com.dune6.springwebservice.constants.ResponseConstants
import com.dune6.springwebservice.exceptions.MessageEmptyException
import com.dune6.springwebservice.exceptions.MessageRecipientInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MessageControllerAdvice {

    //Код состояния ответа HTTP 422 Unprocessable Entity указывает,
    // что сервер понимает тип содержимого в теле запроса
    // и синтаксис запроса является правильным,
    // но серверу не удалось обработать инструкции содержимого.
    @ExceptionHandler(MessageEmptyException::class)
    fun messageEmpty(messageEmptyException: MessageEmptyException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.MESSAGE_EMPTY.value, messageEmptyException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(MessageRecipientInvalidException::class)
    fun messageRecipientInvalid(messageRecipientInvalidException: MessageRecipientInvalidException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(
            ResponseConstants.MESSAGE_RECIPIENT_INVALID.value,
            messageRecipientInvalidException.message
        )
        return ResponseEntity.unprocessableEntity().body(res)
    }

}