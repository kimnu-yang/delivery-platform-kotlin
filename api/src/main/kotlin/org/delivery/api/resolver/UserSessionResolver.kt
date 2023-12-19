package org.delivery.api.resolver

import org.delivery.api.domain.user.model.User
import org.delivery.common.annotation.UserSession
import org.delivery.api.domain.user.service.UserService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserSessionResolver(private val userService: UserService) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션이 있는지 체크
        val annotation = parameter.hasParameterAnnotation(UserSession::class.java)

        // 2. 파라미터 타입 체크
        val parameterType = parameter.parameterType == User::class.java

        return annotation && parameterType
    }

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        // support parameter 에서 true 반환시 여기 실행

        // request context holder에서 값 찾아오기
        val requestContext = RequestContextHolder.getRequestAttributes()
        val userId = requestContext!!.getAttribute("userId", RequestAttributes.SCOPE_REQUEST)

        val userEntity = userService.getUserWithThrow(userId.toString().toLong())

        // 사용자 정보 셋팅
        return User(
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
