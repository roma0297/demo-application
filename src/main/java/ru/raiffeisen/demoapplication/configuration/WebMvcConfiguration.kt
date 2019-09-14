package ru.raiffeisen.demoapplication.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfiguration : WebMvcConfigurerAdapter() {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("index.html")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}
