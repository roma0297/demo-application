package ru.raiffeisen.demoapplication.controllers.auth

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.SignupUserCommand
import ru.raiffeisen.demoapplication.dtos.request.LoginRequestDto
import ru.raiffeisen.demoapplication.dtos.request.SignUpRequest
import ru.raiffeisen.demoapplication.dtos.response.JwtAuthenticationResponse
import ru.raiffeisen.demoapplication.security.JwtTokenProvider
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val tokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager,
    private val signupUserCommand: SignupUserCommand
) {

    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequestDto): ResponseEntity<Any> {

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.usernameOrEmail,
                loginRequest.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val jwt = tokenProvider.generateToken(authentication)
        return ResponseEntity.ok<Any>(JwtAuthenticationResponse(jwt))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any> {
        val userSignupResult = signupUserCommand.process(signUpRequest)
        userSignupResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(errorMessage)
        }
        return ResponseEntity.ok(userSignupResult.value!!)
    }
}
