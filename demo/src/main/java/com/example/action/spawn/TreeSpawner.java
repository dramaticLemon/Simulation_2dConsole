package com.example.action.spawn;

import java.util.function.Predicate;

import com.example.Entity;
import com.example.action.spawn.spawn_rules.Standart;
import com.example.conf.Config;
import com.example.entity.Tree;
import com.example.world_map.Node;
import com.example.world_map.WordMap;;

public class TreeSpawner implements EntitySpawner{
    private final Config config;
    private final SpawnEngine spawnEngine;

    public TreeSpawner(Config config, SpawnEngine spawnEngine) {
        this.config = config;
        this.spawnEngine = spawnEngine;
    }

    @Override
    public void spawn(WordMap map, int count) {
        Predicate<Node> rule = new Standart();
        spawnEngine.spawnEntity(map, Tree::new, count, rule);
    }

    @Override
    public Class<? extends Entity> getEntityType() {
        return Tree.class;
    }

    @Override
    public int getMinRequiredCount() {
        return config.getEntityConfig().TREE;
    }

}
