package org.delivery.api.domain.user.model

import org.delivery.db.user.enums.UserStatus
import java.time.LocalDateTime

data class User (
    val id: Long? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val status: UserStatus? = null,
    val address: String? = null,
    val registeredAt: LocalDateTime? = null,
    val unregisteredAt: LocalDateTime? = null,
    val lastLoginAt: LocalDateTime? = null,
)