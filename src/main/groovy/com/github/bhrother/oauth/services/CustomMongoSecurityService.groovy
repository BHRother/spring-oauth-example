package com.github.bhrother.oauth.services

import com.github.bhrother.oauth.models.User
import com.github.bhrother.oauth.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
/**
 * Created by brunohenriquerother on 14/07/2017.
 */

/**
 * Custom service to retrieve users from database (@see UserDetailsService).
 * This is looking for a user in a MongoDB and converting to a org.springframework.security.core.userdetails.User.
 */
@Service
class CustomMongoSecurityService implements UserDetailsService {

  @Autowired
  UserRepository userRepository

  @Override
  UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username)

    if (user) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user))
    }

    throw new UsernameNotFoundException("No user found for $username")
  }

  private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    List authorities = new ArrayList()
    user.roles.each {authorities.add(new SimpleGrantedAuthority(it.getRole()))}
    authorities
  }
}
