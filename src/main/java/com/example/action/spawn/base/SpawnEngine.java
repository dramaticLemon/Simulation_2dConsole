package com.example.action.spawn.base;

import java.util.function.Predicate;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Entity;
import com.example.Main;
import com.example.manage.MapObjectManager;
import com.example.world_map.Node;
import com.example.world_map.WordMap;

public class SpawnEngine {
    private final MapObjectManager mapObjectManager;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public SpawnEngine(MapObjectManager mapObjectManager) {
        this.mapObjectManager = mapObjectManager;
    }

    public <T extends Entity> void spawnEntity(
        WordMap map,
        Supplier<T> constructor,
        int count,
        Predicate<Node> spawnRule
    ) {
        for (int i = 0; i < count; i++) {
            Node node = null;

            int attempts = 0;
            int maxAttempts = 500;

            while (attempts < maxAttempts) {
                Node candidate = map.getRundomNode();
                if (spawnRule.test(candidate)) {
                    node = candidate;
                    break;
                }
                attempts++;
            }

            if (node == null) {
                logger.warn("Unable to find a suitable cage for the creature. Skipping.");
                continue;
            }

            T obj = constructor.get();
            mapObjectManager.bindObjectToNode(obj, node);
        }
    }
}
