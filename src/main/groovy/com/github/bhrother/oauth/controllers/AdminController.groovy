package com.github.bhrother.oauth.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
/**
 * Created by brunohenriquerother on 14/07/2017.
 *
 * Admin Controller to control and iterate with ROLE_ADMIN access
 */
@RestController
@RequestMapping("/admin")
class AdminController {

  @GetMapping(["/", "/home"])
  ModelAndView admin() {
    new ModelAndView("admin/home", [:])
  }
}
