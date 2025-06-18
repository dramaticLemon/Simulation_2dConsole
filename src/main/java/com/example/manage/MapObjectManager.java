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
 * Bind Class objects and Map node
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
        initrialNode.setObjectLink(obj);
        TypeObject type = getTypeByClass.apply(obj.getClass());
        map.setNodeType(initrialNode, type);
        entityManager.addEntity(obj);
        obj.attachToNode(initrialNode);
    }

    @SuppressWarnings("unchecked")
    public void removeEntity(Entity obj) {
        Node node = obj.getCurrentNode();
        if (node != null) {
            node.setObjectLink(null); // delete lint on object
            map.setNodeType(node, TypeObject.EMPTY);  // change type to null
        }
        // delete obj in object collections
        entityManager.deleteObject((Class<Entity>) obj.getClass(), obj);
        obj.detachNode();
    }

    public void moveEntity(Entity obj, Node newNode) {
        Node current = obj.getCurrentNode();
        if (current != null) {
            current.setObjectLink(null);
            map.setNodeType(current, TypeObject.EMPTY);
        }
        newNode.setObjectLink(obj);
        TypeObject type = getTypeByClass.apply(obj.getClass());
        map.setNodeType(newNode, type);
        obj.attachToNode(newNode); 
    }
}
