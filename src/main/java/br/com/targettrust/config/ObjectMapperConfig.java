package br.com.targettrust.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

/**
 * Generate a Singleton ObjectMapper
 */
public class ObjectMapperConfig {
    private static ObjectMapper INSTANCE;

    // Factory method
    public static ObjectMapper getInstance() {
        // Singleton
        if(INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = new ObjectMapper();
        // this is a performance bottleneck
        INSTANCE.findAndRegisterModules();
        INSTANCE.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        INSTANCE.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        return INSTANCE;
    }
}
