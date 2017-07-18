package com.github.bhrother.oauth.configuration.mongo

import com.mongodb.Mongo
import spock.lang.Specification

/**
 * Created by brunohenriquerother on 18/07/2017.
 */
class EmbeddedMongoConfigurationSpecification extends Specification {

  def 'Start embedded MongoDB' (){
    given:
    def mongoProperties = Mock(MongoProperties)
    def mongoPort = 11024 + Math.abs(new Random().nextInt() % 600)
    EmbeddedMongoConfiguration embeddedMongoConfiguration = new EmbeddedMongoConfiguration(mongoProperties: mongoProperties)
    when:
    Mongo mongo = embeddedMongoConfiguration.mongo()
    then:
    1 * mongoProperties.port >> mongoPort
    mongo.getAddress().host == "127.0.0.1"
    mongo.getAddress().port == mongoPort
    mongo.close()
  }

}
