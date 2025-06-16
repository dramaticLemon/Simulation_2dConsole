package com.example.manage;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.Creatures;
import com.example.Entity;


/**
 * работает с колекциями обьектов 
 * добавляет
 *          удаляет
 *                  выдает
 */
public class EntityManager {
    private static EntityManager instance;
    private final Map<Class<? extends Entity>, Set<Entity>> allEntitiesObjects = new HashMap<>();

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    public <T extends Entity> void addEntity(T instanse) {
        allEntitiesObjects.computeIfAbsent(instanse.getClass(), k -> new HashSet<>()).add(instanse);
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> Set<T> getEntities(Class<T> needClass) {
        return (Set<T>) allEntitiesObjects.getOrDefault(needClass, Collections.emptySet());
    }

    public <T extends Entity> void deleteObject(Class<T> needClass, T instanse) {
        Set<T> entities = getEntities(needClass);
        entities.remove(instanse);
    }
    
    public <T extends Entity> long getSize(Class<T> needClass) {
        return getEntities(needClass).size();
    }


    // TODO удалить
    @Override
    public String toString() {
        return "EntityManager [allEntitiesObjects=" + allEntitiesObjects + "]";
    }

    public Set<Creatures> getAllCreatures() {
        Set<Creatures> result = new HashSet<>();
        for (Set<Entity> entitySet : allEntitiesObjects.values()) {
            for (Entity e : entitySet) {
                if (e instanceof Creatures) {
                    result.add((Creatures) e);
                }
            }
        }
        return result;
    }
}
