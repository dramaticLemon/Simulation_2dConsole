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

public class Predator extends Creatures{
    int attackPower;

    private final static Config config = ConfigService.get().getConfig(); 
    WordMap map = WordMap.getInstance();
    MapObjectManager mapObjectManager = new MapObjectManager();

    public Predator() {
        this.HP = config.getEntityChar().predator_hp;
        this.attackPower = config.getEntityChar().power_attack_predator;
        this.speed = config.getEntityChar().predator_speed;
    }

    public void attack(Creatures obj) {
        obj.changeHP(this.attackPower);
    }

    @Override
    public void changeHP(int value) {
        this.HP -= value;
    }

    @Override
    public void makeMove () {

        for (int i = 0; i < speed; i++) {
            if (!isAlive()) return;
            makeOneStep();
        }
    }
    
    private void makeOneStep() {
        // получить список всех соседних клеток
        List<Node> possibleMoves = map.getNeighbors(getCurrentNode());
        // получить кратчайший маршрут до указанной клетки карты
        Optional<List<Node>> nearestHerbivoreOption = Search.findPath(TypeObject.HERBIVORE, getCurrentNode(), map, TypeObject.PREDATOR);
        if (nearestHerbivoreOption.isPresent()) {
            Node stepToTarget = nearestHerbivoreOption.get().getFirst();
             if (stepToTarget.getType().equals(TypeObject.HERBIVORE)) {
                Herbivore herbivore = (Herbivore) stepToTarget.getObjectLink();
                this.attack(herbivore);
                if (!herbivore.isAlive()) {
                    mapObjectManager.moveEntity(this, stepToTarget);
                }
                return;
            } else if (stepToTarget.getType().equals(TypeObject.EMPTY)) {
                mapObjectManager.moveEntity(this, stepToTarget);
                return; 
           }
        }

        List<Node> validMoves = new ArrayList<>();

        for (Node neighbor: possibleMoves) {
            if (neighbor.getType().equals(TypeObject.EMPTY) || neighbor.getType().equals(TypeObject.HERBIVORE)) {
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
