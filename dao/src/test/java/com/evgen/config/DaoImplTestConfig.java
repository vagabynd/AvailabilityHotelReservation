package com.evgen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoRepositories(basePackages = "com.evgen.dao")
@ComponentScan(basePackages = "com.evgen.dao")
@EnableTransactionManagement
public class DaoImplTestConfig extends AbstractMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "hotel_management_test";
  }

  @Override
  public MongoClient mongoClient() {
    MongoClientURI uri = new MongoClientURI("mongodb://test:qwerty1@ds247410.mlab.com:47410/hotel_management_test");
    return new MongoClient(uri);
  }

  @Override
  protected String getMappingBasePackage() {
    return "com.evgen";
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}