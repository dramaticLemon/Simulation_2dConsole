package com.example.conf;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class ConfigService {
    private static ConfigService instance;
    private final Config config;
    private final String NAME_FILE = "config.yaml";

    private ConfigService() {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(NAME_FILE)) {
            if (in == null) {
                throw new RuntimeException(NAME_FILE + " не найден в ресурсах!");
            }
            config = yaml.loadAs(in, Config.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    public static ConfigService get() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public Config getConfig() {
        return config;
    }
}
