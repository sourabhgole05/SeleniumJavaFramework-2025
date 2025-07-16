package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();
    private static ConfigManager configManager;

    private ConfigManager() {
        try {
            String configFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
            properties.load(new FileInputStream(configFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            synchronized (ConfigManager.class) {
                configManager = new ConfigManager();
            }
        }
        return configManager;
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}