package com.example.conf;

public class Config {
    public MapConfig map;
    public EntityConfig entity_count;
    public EntityChar entity_char;

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

    public static class EntityChar {
        public int herbivore_hp;
        public int predator_hp;
        public int herbivore_speed;
        public int predator_speed;
        public int power_attack_predator;
    }

    public EntityChar getEntityChar() {
        return entity_char;
    }


}

