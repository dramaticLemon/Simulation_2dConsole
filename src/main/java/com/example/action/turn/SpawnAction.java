package com.example.action.turn;

import java.util.HashMap;
import java.util.Map;

import com.example.Entity;
import com.example.action.spawn.GrassSpawner;
import com.example.action.spawn.HerbivoreSpawner;
import com.example.action.spawn.PredatorSpawner;
import com.example.action.spawn.RockSpawner;
import com.example.action.spawn.TreeSpawner;
import com.example.action.spawn.base.EntitySpawner;
import com.example.action.spawn.base.SpawnEngine;
import com.example.conf.Config;
import com.example.manage.EntityManager;
import com.example.world_map.WordMap;

public class SpawnAction {
    private final Map<Class<? extends Entity>, EntitySpawner> spawners = new HashMap<>();
    private final EntityManager entityManager;
    private final WordMap map;
    private final SpawnEngine spawnEngine;

    public SpawnAction(Config config, SpawnEngine spawnEngine, WordMap map, EntityManager entityManager) {
        this.spawnEngine = spawnEngine;
        this.map = map;
        this.entityManager = entityManager;

        
        registerSpawner(new GrassSpawner(config, spawnEngine));
        registerSpawner(new TreeSpawner(config, spawnEngine));
        registerSpawner(new RockSpawner(config, spawnEngine));
        registerSpawner(new PredatorSpawner(config, spawnEngine));
        registerSpawner(new HerbivoreSpawner(config, spawnEngine));
    }

    private void registerSpawner(EntitySpawner spawner) {
        spawners.put(spawner.getEntityType(), spawner);
    }

    public void spawnInitial() {
        for (EntitySpawner spawner : spawners.values()) {
            spawner.spawn(map, spawner.getMinRequiredCount());
        }
    }

    public void maintain() {
        for (EntitySpawner spawner : spawners.values()) {
            int current = (int) entityManager.getSize(spawner.getEntityType());
            int required = spawner.getMinRequiredCount();
            if (current < required) {
                spawner.spawn(map, required - current);
            }
        }
    }

    public <T extends Entity> void spawnSingle(Class<T> needClass) {
        EntitySpawner spawner = spawners.get(needClass);
        if (spawner != null) {
            spawner.spawn(map, 1);
        } else {
            throw new IllegalArgumentException("No spawner registered for class: " + needClass.getSimpleName());
        }
    }
}
