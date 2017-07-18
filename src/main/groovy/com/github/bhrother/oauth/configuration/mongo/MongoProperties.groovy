package com.github.bhrother.oauth.configuration.mongo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "mongo")
class MongoProperties {

  String host
  Integer port
  String dbName

  List<ReplicaSetMember> replicaSetMembers

  static class ReplicaSetMember {
    String host
    Integer port
  }

}
