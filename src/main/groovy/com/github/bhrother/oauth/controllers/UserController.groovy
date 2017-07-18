package com.github.bhrother.oauth.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
/**
 * Created by brunohenriquerother on 14/07/2017.
 *
 * User Controller to control and iterate with ROLE_USER access
 */
@RestController
@RequestMapping("/user")
class UserController {

  @GetMapping("/hello")
  ModelAndView hello() {
    new ModelAndView("user/hello", [:])
  }

}
