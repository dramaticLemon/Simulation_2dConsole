package com.example.action.spawn;

import com.example.Entity;
import com.example.world_map.WordMap;

public interface EntitySpawner {
    /**
     * Спавнит указанное количество сущностей на карте.
     * @param map Карта мира, где происходит спавн.
     * @param count Количество сущностей для спавна.
     */
    void spawn(WordMap map, int count);

    /**
     * Возвращает минимальное необходимое количество сущностей данного типа.
     * @return Минимальное количество.
     */
    int getMinRequiredCount();

    /**
     * Возвращает тип сущности, создаваемой спавнером.
     * @return Класс сущности.
     */
    Class<? extends Entity> getEntityType();
}
