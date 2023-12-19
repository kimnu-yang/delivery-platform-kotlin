package org.delivery.api.domain.user.controller.model

data class UserLoginRequest (
    var email: String? = null,
    var password: String? = null,
)