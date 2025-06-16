package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.Creatures;
import com.example.conf.TypeObject;
import com.example.manage.MapObjectManager;
import com.example.world_map.Node;
import com.example.world_map.Search;
import com.example.world_map.WordMap;

public class Herbivore extends Creatures{
    WordMap map = WordMap.getInstance();
    MapObjectManager mapObjectManager = new MapObjectManager();

    @Override
    public void makeMove () {
        // получить список всех соседних клеток
        List<Node> possibleMoves = map.getNeighbors(getCurrentNode());
        // получить кратчайший маршрут до указанной клетки карты
        Optional<List<Node>> nearestGrassOption = Search.findPath(TypeObject.GRASS, getCurrentNode(), map, TypeObject.HERBIVORE);
        if (nearestGrassOption.isPresent()) {
            Node stepToTarget = nearestGrassOption.get().getFirst();
           // Проверяем, что клетка либо EMPTY, либо GRASS
            if (stepToTarget.getType().equals(TypeObject.GRASS) || stepToTarget.getType().equals(TypeObject.EMPTY)) {
                // Если клетка содержит траву, удаляем её
                if (stepToTarget.getType().equals(TypeObject.GRASS)) {
                    mapObjectManager.removeEntity(stepToTarget.getObjectLink());
                }
                // Перемещаем Herbivore на клетку
                mapObjectManager.moveEntity(this, stepToTarget);
                return;
            }
        }

        List<Node> validMoves = new ArrayList<>();

        for (Node neighbor: possibleMoves) {
            if (neighbor.getType().equals(TypeObject.EMPTY) || neighbor.getType().equals(TypeObject.GRASS)) {
                validMoves.add(neighbor);
            }
        }
        if (!validMoves.isEmpty()) {

            Random random = new Random();
            Node nextNode = validMoves.get(random.nextInt(validMoves.size()));
            System.out.println("Rundom move");
            mapObjectManager.moveEntity(this, nextNode);
        }
    }
}
