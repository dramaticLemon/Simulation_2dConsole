package com.example;

import com.example.action.spawn.base.SpawnEngine;
import com.example.action.turn.MoveAction;
import com.example.action.turn.SpawnAction;
import com.example.conf.Config;
import com.example.conf.ConfigService;
import com.example.manage.EntityManager;
import com.example.manage.MapObjectManager;
import com.example.world_map.WordMap;
import com.example.world_map.rendering.Render;
import com.example.world_map.rendering.RenderMapTerminal;

public class Simulation {
    private final Render renderEngine = new RenderMapTerminal();
    private final EntityManager entityManager = EntityManager.getInstance();
    private final static Config config = ConfigService.get().getConfig(); 
    private final MapObjectManager mapObjectManager = new MapObjectManager();
    private final SpawnEngine spawnEngine = new SpawnEngine(mapObjectManager);
    private final WordMap wordMap = WordMap.getInstance();
    private final SpawnAction spawnAction = new SpawnAction(config, spawnEngine, wordMap, entityManager);
    private final MoveAction moveAction = new MoveAction(entityManager);

    private volatile boolean running;
    private volatile boolean stopped = false;
    private int delay = 1000;

     public void stop() {
        stopped = !stopped;
    }
    public void finish() {
        running = false;
    }

    public void startSimulation() {
        running = true;
        spawnAction.spawnInitial();
        while (running) {
            nextTurn();
        }
    }

    private void nextTurn() {

        if (!stopped) {
            renderEngine.renderMap();
            moveAction.performMove();
            spawnAction.maintain();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setDelay(int volume) {
        this.delay = volume;
    }
}
