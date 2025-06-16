package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.Creatures;
import com.example.conf.Config;
import com.example.conf.ConfigService;
import com.example.conf.TypeObject;
import com.example.manage.MapObjectManager;
import com.example.world_map.Node;
import com.example.world_map.Search;
import com.example.world_map.WordMap;

public class Herbivore extends Creatures{
    private final static Config config = ConfigService.get().getConfig(); 
    WordMap map = WordMap.getInstance();
    MapObjectManager mapObjectManager = new MapObjectManager();
    boolean alived = true;

    public Herbivore() {
        this.HP = config.getEntityChar().herbivore_hp;
    }

    public boolean isAlive() {
        return this.alived;
    }

    @Override
    public void changeHP(int value) {
        if (this.HP < 1) {
            this.alived = false;
            mapObjectManager.removeEntity(this);
            return;
        }
        this.HP -= value;
    }

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
            mapObjectManager.moveEntity(this, nextNode);
        }
    }

}
