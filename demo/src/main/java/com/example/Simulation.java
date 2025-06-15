package com.example;

import java.util.Set;

import com.example.action.GrassSpawner;
import com.example.action.HarbivoreSpawner;
import com.example.action.PredatorSpawner;
import com.example.action.RockSpawner;
import com.example.action.TreeSpawner;
import com.example.conf.Config;
import com.example.conf.ConfigService;

public class Simulation {
    WordMap map = WordMap.getInstance();
    // MoveCount
    // RenderMap
    private final RenderMap renderMap = new RenderMap();
    private final Config config = ConfigService.get().getConfig();
    private final EntityManager entityManager = EntityManager.getInstance();
    // Actions ( init Actions)


    void nextTurn() {
        // рендер хода
        // вызвать у всех Creatures move
        Set<Herbivore> herbivores = entityManager.getEntities(Herbivore.class);
        for (Herbivore obj: herbivores) {
            obj.makeMove();
        }
        Set<Predator> predators = entityManager.getEntities(Predator.class);
        for (Predator obj: predators) {
            obj.makeMove();
        }

    }

    void startSimulation() {
        initActions();
        while (true) {
            nextTurn();
            renderMap.render();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
        

    }

    private void initActions() {
        placeObjectsOnTheMap();
        placeCreatureOnTheMap();
    }

    private void placeCreatureOnTheMap() {
        HarbivoreSpawner.spawnInitAction(map, config.getEntityConfig().HERBIVORE);
        PredatorSpawner.spawnInitAction(map, config.getEntityConfig().PREDATOR);
    }

    private void placeObjectsOnTheMap() {
        TreeSpawner.spawnInitAction(map, config.getEntityConfig().TREE);
        RockSpawner.spawnInitAction(map, config.getEntityConfig().ROCK);
        GrassSpawner.spawnInitAction(map, config.getEntityConfig().GRASS);
    }
    @SuppressWarnings("unused")
    void pauseSimulation() {

    }


}
