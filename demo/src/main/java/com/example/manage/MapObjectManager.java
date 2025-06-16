package com.example.manage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.Entity;
import com.example.conf.TypeObject;
import com.example.entity.Grass;
import com.example.entity.Herbivore;
import com.example.entity.Predator;
import com.example.entity.Rock;
import com.example.entity.Tree;
import com.example.world_map.Node;
import com.example.world_map.WordMap;


/**
 * работает с обьектами и клетками карты (привязывает, отвязывает обьекты, пермещает)
 */
public class MapObjectManager {
    private final WordMap map = WordMap.getInstance();
    private final EntityManager entityManager = EntityManager.getInstance();

    private static final Map<Class<? extends Entity>, TypeObject> typeByClass = new HashMap<>() {{
        put(Rock.class, TypeObject.ROCK);
        put(Grass.class, TypeObject.GRASS);
        put(Tree.class, TypeObject.TREE);
        put(Herbivore.class, TypeObject.HERBIVORE);
        put(Predator.class, TypeObject.PREDATOR);
    }};

    public static final Function<Class<? extends Entity>, TypeObject> getTypeByClass = cls -> {
        TypeObject type = typeByClass.get(cls);
        if (type == null) {
            throw new IllegalArgumentException("No TypeObject mapping for class: " + cls.getSimpleName());
        }
        return type;
    };

    public void bindObjectToNode(Entity obj, Node initrialNode) {
        // связь клетку карты и обьекта
        initrialNode.setObjectLink(obj);
        // изменение типа обьекта в клетке карты
        TypeObject type = getTypeByClass.apply(obj.getClass());
        map.setNodeType(initrialNode, type);
        // добавить обьект в массив обектов
        entityManager.addEntity(obj);
        obj.attachToNode(initrialNode); // привязать обьект к клетке карты
    }

    /**
     * Удалить объект с карты
     */
    @SuppressWarnings("unchecked")
    public void removeEntity(Entity obj) {
        Node node = obj.getCurrentNode();
        if (node != null) {
            node.setObjectLink(null); // удалить ссылку на обьект
            map.setNodeType(node, TypeObject.EMPTY);  // поменять тип на пустой
        }
        // удалить обьект из коллекции
        entityManager.deleteObject((Class<Entity>) obj.getClass(), obj);
        obj.detachNode(); // отвязать обьект от клетки карты
    }

    /**
     * Переместить объект в другую клетку
     */
    public void moveEntity(Entity obj, Node newNode) {
        Node current = obj.getCurrentNode(); // текущая клетка под обьектов
        if (current != null) {
            current.setObjectLink(null); // Обновить старую клетку сбросить ссылку
            map.setNodeType(current, TypeObject.EMPTY); // обновить старую клетку сбросить тип
        }
        newNode.setObjectLink(obj); // поменять ссылку с null на обьект который стал на клетку
        TypeObject type = getTypeByClass.apply(obj.getClass());
        map.setNodeType(newNode, type); // меняем тип новой клетки в соотв с обьектом который стоит на ней
        // меняем связь текущей ноды в обьекте с клеткой
        obj.attachToNode(newNode); 
    }
}
