package com.github.bhrother.oauth.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
/**
 * Created by brunohenriquerother on 18/07/2017.
 */
@RestController
class AuthorizationController {

  @GetMapping("/login")
  ModelAndView login() {
    new ModelAndView("login", [:])
  }

  @GetMapping("/403")
  ModelAndView error403() {
    new ModelAndView("error/403", [:])
  }

  @GetMapping(value = ["/", "/home"])
  ModelAndView home() {
    new ModelAndView("home", [:])
  }
}
