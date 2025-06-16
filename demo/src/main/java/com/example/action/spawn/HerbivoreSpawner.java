package com.example.action.spawn;

import java.util.function.Predicate;

import com.example.Entity;
import com.example.action.spawn.spawn_rules.NoPredatorsNearly;
import com.example.conf.Config;
import com.example.entity.Herbivore;
import com.example.world_map.Node;
import com.example.world_map.WordMap;

public class HerbivoreSpawner implements EntitySpawner{
    private final Config config;
    private final SpawnEngine spawnEngine;

    public HerbivoreSpawner(Config config, SpawnEngine spawnEngine) {
        this.config = config;
        this.spawnEngine = spawnEngine;
    }

    @Override
    public void spawn(WordMap map, int count) {
        Predicate<Node> rule = new NoPredatorsNearly(map);
        spawnEngine.spawnEntity(map, Herbivore::new, count, rule);
    }

    @Override
    public Class<? extends Entity> getEntityType() {
        return Herbivore.class;
    }

    @Override
    public int getMinRequiredCount() {
        return config.getEntityConfig().HERBIVORE;
    }

}