package com.datastax.yasa.solutions;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.yasa.ApolloConfiguration;

/**
 * With this Exercice you will learn how to read data from {@link ResultSet}.
 */
public class Exercise4_ListUsers {

    // Logger for the class
    private static final Logger LOGGER = LoggerFactory.getLogger(Exercise4_ListUsers.class);
    
    /** 
     * - ACTION #1 - 
     * 
     * Parse {@link ResultSet} to display firstname and lastname in the console.
     * 
     * Documentation:
     *  - https://docs.datastax.com/en/developer/java-driver/4.2/manual/core/#running-queries
     */
    private static void displayUsers(ResultSet rs) {
        // Change the line below to :
        // (1) Check that rs is not null, javaNullPointerException are ALWAYS YOUR fault, no excuse
        // (2) Iterate on rows, for each row use the LOGGER to show firstname and lastname values
        throw new RuntimeException("Exercise4: displayUsers method should be implemented");
    }
    
    /** 
     * Main CLASS
     */
    public static void main(String[] args) {
        LOGGER.info("Starting exercice 4...");
        
        try (CqlSession cqlSession = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(ApolloConfiguration.PATH_TO_ZIP))
                .withAuthCredentials(ApolloConfiguration.USERNAME,ApolloConfiguration.PASSWORD)
                .withKeyspace(ApolloConfiguration.KEYSPACENAME)
                .build()) {

            // Prepared Once
            PreparedStatement query = cqlSession.prepare(
                    SimpleStatement.builder("SELECT * FROM users").build());

            // Execute Many
            ResultSet rs = cqlSession.execute(query.bind());
            
            // Display Result
            displayUsers(rs);
        }
        LOGGER.info("===================================");
        LOGGER.info("====      Exercise 4 - OK      ====");
        LOGGER.info("===================================");
    }
    
    
}
