//package ru.raiffeisen.demoapplication.configuration
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.Ordered
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
//
//@Configuration
//class WebMvcConfiguration : WebMvcConfigurer {
//    override fun addViewControllers(registry: ViewControllerRegistry) {
//        registry.addViewController("/").setViewName("index.html")
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
//    }
//
//    override fun addResourceHandlers(resourceHandlerRegistry: ResourceHandlerRegistry) {
//        resourceHandlerRegistry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
//    }
//}
