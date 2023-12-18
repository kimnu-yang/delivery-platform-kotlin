package org.delivery.api.config.objectmapper

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        // kotlin module 설정
        val kotlinModule = KotlinModule.Builder().apply {
            withReflectionCacheSize(512)
            configure(KotlinFeature.NullToEmptyCollection, false)
            configure(KotlinFeature.NullToEmptyMap, false)
            configure(KotlinFeature.NullIsSameAsDefault, false)
            configure(KotlinFeature.SingletonSupport, false)
            configure(KotlinFeature.StrictNullChecks, false)
        }.build()

        // java module 설정
        val objectMapper = ObjectMapper().apply {
            registerModules(Jdk8Module())
            registerModule(JavaTimeModule())
            registerModule(kotlinModule)

            //모르는 json filed를 무시한다.
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)

            //날짜 관련 직렬화 설정
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

            //스네이크 케이스 설정
            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        }

        return objectMapper
    }
}