package com.sagar.jpa.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.jwt")
class JWTConfig {

    lateinit var secretkey: String
    lateinit var tokenPrefix: String
    var tokenExpirationDays: Int = 0
}