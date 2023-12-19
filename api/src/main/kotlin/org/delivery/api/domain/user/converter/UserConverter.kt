package org.delivery.api.domain.user.converter

import org.delivery.api.domain.user.controller.model.UserRegisterRequest
import org.delivery.api.domain.user.controller.model.UserResponse
import org.delivery.common.annotation.Converter
import org.delivery.db.user.UserEntity

@Converter
class UserConverter {

    fun toEntity(
        request: UserRegisterRequest?
    ): UserEntity {

        return UserEntity(
            name = request?.name,
            email = request?.email,
            password = request?.password,
            address = request?.address,
        )
    }

    fun toResponse(
        userEntity: UserEntity
    ): UserResponse {

        return UserResponse(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            password = userEntity.password,
            status = userEntity.status,
            address = userEntity.address,
            registeredAt = userEntity.registeredAt,
            unregisteredAt = userEntity.unregisteredAt,
            lastLoginAt = userEntity.lastLoginAt
        )
    }
}