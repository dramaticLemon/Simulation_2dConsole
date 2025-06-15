package com.example.conf;

public class Config {
    public MapConfig map;
    public EntityConfig entity_count;

    public static class MapConfig {
        public int width;
        public int heigth;
    }

    public MapConfig getMapConfig() {
        return map;
    }

    public static class EntityConfig {
        public int HERBIVORE;
        public int ROCK;
        public int TREE;
        public int GRASS;
        public int PREDATOR;
    }

    public EntityConfig getEntityConfig() {
        return entity_count;
    }


}

