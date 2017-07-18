package com.github.bhrother.oauth.configuration.mongo

import com.mongodb.Mongo
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.github.bhrother.oauth")
@EnableConfigurationProperties(MongoProperties)
class EmbeddedMongoConfiguration extends AbstractMongoConfiguration {

  @Autowired
  MongoProperties mongoProperties

  @Override
  Mongo mongo() throws Exception {
    new EmbeddedMongoBuilder()
        .version("2.6.8")
        .bindIp("127.0.0.1")
        .port(mongoProperties.port)
        .build()
  }

  @Override
  protected String getDatabaseName() {
    mongoProperties.dbName
  }
}