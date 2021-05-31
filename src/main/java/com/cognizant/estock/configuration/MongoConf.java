//package com.cognizant.estock.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//
//@Configuration
//public class MongoConf {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public MongoDatabaseFactory mongoDbFactory() {
//        return new SimpleMongoClientDatabaseFactory(env.getProperty("spring.data.mongodb.uri"));
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoDbFactory());
//    }
//}
