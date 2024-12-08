package org.example.cctvsmarttraffic.Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "org.example.cctvsmarttraffic.Repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "cctv_db"; // Specify your database name
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        // Corrected connection string with no extra spaces
        return MongoClients.create("mongodb://root:admin@ec2-100-26-248-255.compute-1.amazonaws.com:27017");
    }
}
