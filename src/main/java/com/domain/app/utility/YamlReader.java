package com.domain.app.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.domain.app.dto.TestConfig;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class YamlReader {
    private YamlReader() {
        //prevent outside instantiation
    }

    /**
     * This reads the config.yaml file under src/main/resources and maps the value to TestConfig.class
     * @return TestConfiguration Object
     */
    public static TestConfig loadFromYamlFile() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("config.yml")).getFile());
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TestConfig config = null;
        try {
            config = objectMapper.readValue(file, TestConfig.class);
        } catch (IOException e) {
            //Add logs
        }
        return config;
    }
}
