package com.datastax.yasa.solutions;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.yasa.ApolloConfiguration;

/**
 * With this Exercice you will prepare your previous {@link SimpleStatement} to get a {@link PreparedStatement}.
 */
public class Exercise3_CreatePreparedStatement {

    // Logger for the class
    private static final Logger LOGGER = LoggerFactory.getLogger(Exercise3_CreatePreparedStatement.class);
   
    /** 
     * - ACTION #1 - 
     * 
     * Based on the work you did in previous exercise, prepare the insert statement
     * 
     * Documentation:
     *  - https://docs.datastax.com/en/developer/java-driver/4.2/manual/core/statements/prepared/#preparing
     */
    private static PreparedStatement prepareStatementOnce(CqlSession cqlSession) {
        return cqlSession.prepare(
                SimpleStatement.builder(""
                        + "INSERT INTO users (firstName,lastName,age,city,email) "
                        + "VALUES (?,?,?,?,?)").build());
    }
    
    /** 
     * - ACTION #2 - 
     * 
     * Use the prepared statement to bind parameters and create a {@link BoundStatement}.
     * 
     * Documentation:
     *  - https://docs.datastax.com/en/developer/java-driver/4.2/manual/core/statements/prepared/#parameters-and-binding
     */
    private static BoundStatement bindQuery(PreparedStatement queryInsertUser, 
            String firstName, String lastName, int age, String city, String email) {
        LOGGER.info(" + Insert {} {}", firstName, lastName);
        // Change this line to create and return the expected BoundStatement 
        throw new RuntimeException("Exercise3: bindQuery method should be implemented");
    }
    
    /** 
     * Main CLASS
     */
    public static void main(String[] args) {
        LOGGER.info("Starting exercise 3...");
        
        try (CqlSession cqlSession = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(ApolloConfiguration.PATH_TO_ZIP))
                .withAuthCredentials(ApolloConfiguration.USERNAME,ApolloConfiguration.PASSWORD)
                .withKeyspace(ApolloConfiguration.KEYSPACENAME)
                .build()) {

            // Prepared Once
            PreparedStatement query = prepareStatementOnce(cqlSession);

            // Execute Many
            cqlSession.execute(bindQuery(query, "Arya", "Stark", 16, "Winterfell", "aray.stark@got.com"));
            cqlSession.execute(bindQuery(query, "Jamie", "Lannister", 32, "Kingslandhill", "jamie@got.com"));
            cqlSession.execute(bindQuery(query, "Ramsay", "Bolton", 42, "CastleRock", "ramsay@got.com"));
        }
        LOGGER.info("===================================");
        LOGGER.info("====      Exercise 3 - OK      ====");
        LOGGER.info("===================================");
    }
    
    
    
}
