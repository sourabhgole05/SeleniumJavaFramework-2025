package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;
    
    private ConfigManager() {
        loadProperties();
    }
    
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
            
            // Override with system properties if they exist
            properties.putAll(System.getProperties());
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    
    public int getInt(String key) {
        return Integer.parseInt(getProperty(key));
    }
}