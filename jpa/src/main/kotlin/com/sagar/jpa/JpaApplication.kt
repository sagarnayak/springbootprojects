package com.sagar.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@SpringBootApplication
class JpaApplication


fun main(args: Array<String>) {
    runApplication<JpaApplication>(*args)
}

@Bean
fun localeResolver(): LocaleResolver {
    val resolver = AcceptHeaderLocaleResolver()
    resolver.defaultLocale = Locale.US
    return resolver
}