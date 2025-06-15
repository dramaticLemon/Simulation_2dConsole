package com.example.action;

import java.util.function.Predicate;
import java.util.function.Supplier;

import com.example.Entity;
import com.example.MapObjectManager;
import com.example.Node;
import com.example.WordMap;

public interface SpawnAction {
    static MapObjectManager mapObjectManager = new MapObjectManager();

    static void spawnInitAction(WordMap map, int count) {};

    static <T extends Entity> void spawnEntity(
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
            mapObjectManager.boundedOjectAndNode(obj, node);
            
        }
    }
}
