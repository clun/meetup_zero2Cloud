package com.datastax.yasa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;

/**
 * With this Exercise you will create a {@link CqlSession} object
 */
public class Exercise1_ConnectAndCreateSchema {
  
    // Logger for the class
    private static final Logger LOGGER = LoggerFactory.getLogger(Exercise1_ConnectAndCreateSchema.class);
   
    /** 
     * - ACTION #1 -
     * 
     * Using `CqlSession.Builder` please initialize a `CqlSession`.
     *      - providing 2 Contact Points
     *      - providing the Datacenter name
     *      
     * Tips :
     * - https://docs.datastax.com/en/developer/java-driver/4.1/manual/core/
     * - https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/net/InetSocketAddress.html
     */
    public static void main(String[] args) {
        LOGGER.info("Starting exercise 1...");
        
        // Change the line below to fill the builder object
        try (CqlSession cqlSession = CqlSession.builder().build()) {
            // Show list of keyspaces in the logs
            LOGGER.info("Your Keyspace is " + cqlSession.getKeyspace().get().asInternal());
        }
        
        LOGGER.info("===================================");
        LOGGER.info("====      Exercise 1 - OK      ====");
        LOGGER.info("===================================");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* SOLUTION
     * 
     * 
     *  try (CqlSession cqlSession = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(ApolloConfiguration.PATH_TO_ZIP))
                .withAuthCredentials(ApolloConfiguration.USERNAME,ApolloConfiguration.PASSWORD)
                .withKeyspace(ApolloConfiguration.KEYSPACENAME)
                .build()) {
     */
    
}
