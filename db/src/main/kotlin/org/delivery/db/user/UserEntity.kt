package org.delivery.db.user

import jakarta.persistence.*
import org.delivery.db.user.enums.UserStatus
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class UserEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,

    @field:Column(length = 50, nullable = false)
    var name: String? = null,

    @field:Column(length = 100, nullable = false)
    var email: String? = null,

    @field:Column(length = 100, nullable = false)
    var password: String? = null,

    @field:Column(length = 50, nullable = false)
    @field:Enumerated(EnumType.STRING)
    var status: UserStatus? = null,

    @field:Column(length = 150, nullable = false)
    var address: String? = null,

    var registeredAt: LocalDateTime? = null,

    var unregisteredAt: LocalDateTime? = null,

    var lastLoginAt: LocalDateTime? = null
){
    override fun toString(): String {
        return "UserEntity(id=$id, name=$name, email=$email, password=$password, status=$status, address=$address, registeredAt=$registeredAt, unregisteredAt=$unregisteredAt, lastLoginAt=$lastLoginAt)"
    }
}