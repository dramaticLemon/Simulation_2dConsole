package com.example.action.spawn.base;

import java.util.function.Predicate;
import java.util.function.Supplier;

import com.example.Entity;
import com.example.manage.MapObjectManager;
import com.example.world_map.Node;
import com.example.world_map.WordMap;

public class SpawnEngine {
    private final MapObjectManager mapObjectManager;

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
                System.out.println("Unable to find a suitable cage for the creature. Skipping.");
                continue;
            }

            T obj = constructor.get();
            mapObjectManager.bindObjectToNode(obj, node);
        }
    }
}
