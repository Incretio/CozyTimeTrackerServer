package com.incretio.cozy_time_tracker_server.config;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationProperties {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());
    private final Properties unionProperties = new Properties();

    public ApplicationProperties(@Context ServletContext servletContext) {
        String siteHome = servletContext.getRealPath("/");
        addProperty("site.home", siteHome);
        Properties confProperties = loadPropsFromPath(siteHome, "WEB-INF/config/conf.properties");
        addProperties(confProperties);
        Properties confExProperties = loadPropsFromPath(siteHome, "WEB-INF/config/conf_ex.properties");
        addProperties(confExProperties);
    }

    public boolean contains(String key) {
        return unionProperties.containsKey(key);
    }

    public String getProperty(String key) {
        return unionProperties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return unionProperties.getProperty(key, defaultValue);
    }

    private Properties loadPropsFromPath(String path, String fileName) {
        return loadPropsFromPath(path.concat(fileName));
    }

    private Properties loadPropsFromPath(String path) {
        Properties result = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            result.load(fis);
        } catch (FileNotFoundException e) {
            String template = "Error loading resource with path %s. File not found.";
            LOGGER.log(Level.WARNING, String.format(template, path));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error loading Properties.", e);
        }
        return result;
    }

    private void addProperty(String key, String value) {
        unionProperties.put(key, value);
    }

    private void addProperties(Properties properties) {
        for (Map.Entry<Object, Object> element : properties.entrySet()) {
            unionProperties.put(element.getKey(), element.getValue());
        }
    }

}