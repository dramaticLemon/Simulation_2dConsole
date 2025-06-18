package com.example.action.spawn.base;

import com.example.Entity;
import com.example.world_map.WordMap;

public interface EntitySpawner {

    /**
     * Spawn specified count entity on map.
     * @param map Game map, where spawner entity.
     * @param count Count entity from spawn.
     */
    void spawn(WordMap map, int count);

    /**
     * Return minimal count entity current tupe.
     * @return minimal value.
     */
    int getMinRequiredCount();

    /**
     * Returned entity type.
     * @return entity class.
     */
    Class<? extends Entity> getEntityType();
}
