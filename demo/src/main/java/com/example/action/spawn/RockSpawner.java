package com.example.action.spawn;

import java.util.function.Predicate;

import com.example.Entity;
import com.example.action.spawn.spawn_rules.Standart;
import com.example.conf.Config;
import com.example.entity.Rock;
import com.example.world_map.Node;
import com.example.world_map.WordMap;


public class RockSpawner implements  EntitySpawner{
    private final Config config;
    private final SpawnEngine spawnAction;

    public RockSpawner(Config config, SpawnEngine spawnAction) {
        this.config = config;
        this.spawnAction = spawnAction;
    }

    @Override
    public void spawn(WordMap map, int count) {
        Predicate<Node> rule = new Standart();
        spawnAction.spawnEntity(map, Rock::new, count, rule);
    }

    @Override
    public Class<? extends Entity> getEntityType() {
        return Rock.class;
    }

    @Override
    public int getMinRequiredCount() {
        return config.getEntityConfig().ROCK;
    }

}
