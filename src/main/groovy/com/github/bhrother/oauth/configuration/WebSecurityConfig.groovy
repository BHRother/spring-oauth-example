package com.github.bhrother.oauth.configuration

import com.github.bhrother.oauth.error.CustomAccessDeniedHandler
import com.github.bhrother.oauth.models.Role
import com.github.bhrother.oauth.models.User
import com.github.bhrother.oauth.repositories.UserRepository
import com.github.bhrother.oauth.services.CustomMongoSecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomMongoSecurityService customMongoSecurityService

  @Autowired
  UserRepository userRepository

  @Autowired
  private CustomAccessDeniedHandler accessDeniedHandler

  static final String ROLE_ADMIN = "ADMIN"
  static final String ROLE_USER = "USER"

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/css/*", "/js/*").permitAll()
          .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
          .antMatchers("/user/**").hasRole(ROLE_USER)
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .and()
        .logout()
          .permitAll()
          .and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
  }

  /**
   * Configure the service responsible to retrieve users.
   * There are 2 types here:
   *
   * * Memory - Users exists oly in memory
   * * Custom - Using MongoDB to store and retrieve users {@see CustomMongoSecurityService}
   *
   * @param auth
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customMongoSecurityService).and()
        .inMemoryAuthentication()
          .withUser("user").password("user").roles(ROLE_USER).and()
          .withUser("admin").password("1234").roles(ROLE_ADMIN)

    userRepository.insert([
        new User(username: "usermongo", password: "usermongo", roles: [new Role(role: "ROLE_USER", roleDescription: "User role access")]),
        new User(username: "adminmongo", password: "adminmongo", roles: [new Role(role: "ROLE_ADMIN", roleDescription: "ADMIN role access")])
    ])
  }
}
