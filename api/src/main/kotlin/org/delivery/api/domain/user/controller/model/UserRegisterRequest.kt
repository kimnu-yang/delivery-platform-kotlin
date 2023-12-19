package org.delivery.api.domain.user.controller.model

data class UserRegisterRequest (
    var name: String? = null,
    var email: String? = null,
    var address: String? = null,
    var password: String? = null
)