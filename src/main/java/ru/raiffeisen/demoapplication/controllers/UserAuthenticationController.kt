package ru.raiffeisen.demoapplication.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import java.security.Principal
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class UserAuthenticationController {
    @RequestMapping("/user")
    fun getUser(user: Principal): Principal = user
}
