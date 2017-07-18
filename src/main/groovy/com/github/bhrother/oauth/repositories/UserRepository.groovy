package com.github.bhrother.oauth.repositories

import com.github.bhrother.oauth.models.User
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by brunohenriquerother on 14/07/2017.
 */
interface UserRepository extends MongoRepository<User, String>{

  User findByUsername(String username)
}