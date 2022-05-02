package com.dune6.springwebservice.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
@EntityListeners(UserListener::class)
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())

    @Column(unique = true)
    @Size(min = 2)
    var username: String = ""

    @Size(min = 8, max = 15)
    @Column(unique = true)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    var phoneNumber: String = ""

    @Size(min = 60, max = 60)
    var password: String = ""
    var status: String = "available"

    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accountStatus: String = "activated"

}