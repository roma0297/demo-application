package ru.raiffeisen.demoapplication.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import ru.raiffeisen.demoapplication.services.UserService

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    val userService: UserService
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/**", "index.html").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login").failureUrl("/login-error");
    }
}
