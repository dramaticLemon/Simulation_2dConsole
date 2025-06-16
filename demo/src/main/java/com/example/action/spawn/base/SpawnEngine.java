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
            Node node;
            // TODO возможно бесконечный цикл
            do {
               node = map.getRundomNode();
            } while (!spawnRule.test(node));

            T obj = constructor.get(); // получить обьект
            mapObjectManager.bindObjectToNode(obj, node);
            
        }
    }
}
