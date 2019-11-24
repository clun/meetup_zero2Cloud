package com.datastax.yasa;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;

/**
 * With this Exercise you will learn how to create a {@link SimpleStatement} object
 */
public class Exercise2_CreateSimpleStatement {

    // Logger for the class
    private static final Logger LOGGER = LoggerFactory.getLogger(Exercise2_CreateSimpleStatement.class);
    
    /** 
     * - ACTION #1 - 
     * 
     * Create a statement in order to insert a User in the `user` Table.
     * 
     * Here a sample cql statement to help you:
     * INSERT INTO users (firstname,lastname,age,city,email) 
     * VALUES ('Jon', 'Snow', 35, 'Winterfell', 'jon.snow@got.com')
     * 
     * Tips:
     *  - https://docs.datastax.com/en/developer/java-driver/latest/manual/core/statements/simple/
     *  - You can use DataStax Studio to check if users have been created. (select * from users)
     */
    private static SimpleStatement insertUserSimple(String firstName, String lastName, int age, String city, String email) {
        LOGGER.info(" + Insert {} {}", firstName, lastName);
        return SimpleStatement.builder("<YOUR_STATEMENT>").build();
    }
    
    /** 
     * Main Function
     */
    public static void main(String[] args) {
        LOGGER.info("Starting exercise 2...");
        
        // We did that for you this time
        try (CqlSession cqlSession = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(ApolloConfiguration.PATH_TO_ZIP))
                .withAuthCredentials(ApolloConfiguration.USERNAME,ApolloConfiguration.PASSWORD)
                .withKeyspace(ApolloConfiguration.KEYSPACENAME)
                .build()) {
            
            // Create expected Table (if not exist)
            cqlSession.execute(
                    SchemaBuilder.createTable(ApolloConfiguration.KEYSPACENAME, "users")
                    .ifNotExists()
                    .withPartitionKey("lastname", DataTypes.TEXT)
                    .withColumn("firstname", DataTypes.TEXT)
                    .withColumn("age", DataTypes.INT)
                    .withColumn("city", DataTypes.TEXT)
                    .withColumn("email", DataTypes.TEXT)
                    .build());
            LOGGER.info("Table '{}' has been ", "users");
            
            // Insert some Data (upserts)
            cqlSession.execute(insertUserSimple("john", "snow", 30, "Winterfell", "jon.snow@got.com"));
            cqlSession.execute(insertUserSimple("Sansa", "Stark", 20, "Winterfell", "sansa.stark@got.com"));
        }
        
        LOGGER.info("===================================");
        LOGGER.info("====      Exercise 2 - OK      ====");
        LOGGER.info("===================================");
    }
    
}
