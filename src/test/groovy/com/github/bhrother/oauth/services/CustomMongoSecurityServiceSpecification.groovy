package com.github.bhrother.oauth.services

import com.github.bhrother.oauth.models.Role
import com.github.bhrother.oauth.models.User
import com.github.bhrother.oauth.repositories.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification
/**
 * Created by brunohenriquerother on 18/07/2017.
 */
class CustomMongoSecurityServiceSpecification extends Specification {

  def userRepository = Mock(UserRepository)
  def customMongoSecurityService = new CustomMongoSecurityService(userRepository: userRepository)

  def "Should return a Spring User with roles if the username exists"() {
    given:
    def username = "validUser"
    def userDB = new User(username: username, password: "123", roles: [new Role(role: "USER")])
    when:
    def springUserFound = customMongoSecurityService.loadUserByUsername(username)
    then:
    1 * userRepository.findByUsername(username) >> userDB
    springUserFound
    springUserFound.username == username
    springUserFound.authorities.size() == 1
    springUserFound.authorities[0].authority == "USER"
  }

  def "Should throw UsernameNotFoundException if the username does not exist"() {
    given:
    def username = "notValidUser"
    when:
    customMongoSecurityService.loadUserByUsername(username)
    then:
    1 * userRepository.findByUsername(username) >> null
    thrown(UsernameNotFoundException)
  }
}
