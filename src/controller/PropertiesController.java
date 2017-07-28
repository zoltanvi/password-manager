package controller;

import java.io.IOException;
import java.util.Properties;

public class PropertiesController {
    Properties props;

    /** Constructor for the PropertiesController */
    public PropertiesController(){
        props = new Properties();
        try {
            props.load(
                Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("props.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for properties
     * @param key is the key from the 'key=value' pairs
     * @return the value from the 'key=value' pair
     */
    public String getProp(String key){
        return props.getProperty(key);
    }
}
