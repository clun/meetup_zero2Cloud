package com.datastax.yasa;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

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
        if (null != rs) {
            LOGGER.info("Total items on page 1 " + rs.getAvailableWithoutFetching());
            for (Row row : rs) {
                LOGGER.info("+ User: {} {}", row.getString("firstname"), row.getString("lastname"));
            }
        }
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
