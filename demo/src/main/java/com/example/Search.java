package com.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class Search {
     /**
     * Поиск в ширину с возвратом пути узлов
     * @param value Значение узла для поиска
     * @param startNode Текущий узел с которого будет стартовать поиск
     * @param map собственно карта
     * @return списков кратчайшего пути узлов
     */
    public static Optional<List<Node>> findPath(
        TypeObject searchValue,
        Node startNode,
        WordMap map,
        TypeObject typeObject
        ) {

        if (startNode == null || searchValue == null || typeObject == null) {
            return Optional.empty();
        }

        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> alreadyVisited = new HashSet<>();// локальный сет для отслеживания посещаемых узлов
        Map<Node, Node> parentMap = new HashMap<>(); // хранение родительских узлов: откуда пришли к текущему

        // добавить стартовый узел в очередь и пометить как посещенный
        queue.add(startNode);
        alreadyVisited.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();

            // проверка, является ли текущий узел искомым
            if (currentNode.getType().equals(searchValue)) {
                return Optional.of(reconstructionPath(parentMap, startNode, currentNode));
            }

            for (Node neighbor : map.getNeighbors(currentNode)) {
                if (!alreadyVisited.contains(neighbor) && isPassable(neighbor, typeObject)) {
                    alreadyVisited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, currentNode);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Вспомогательный метод для восстановления пути от целевого узла к стартовому
     * используя карту родителей
     *
     * @param parentMap Карта, где ключ - узел, значение - его родитель в BFS
     * @param startNode Начальный узел пути
     * @param endNode Конечный (целевой) узел пути
     * @return Список узлов, предоставляющий путь от startNode до EndNode
     */
    private static List<Node> reconstructionPath(Map<Node, Node> parentMap, Node startNode, Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        // идем от конечного узла назад к стартовому
        while (current != null && ! current.equals(startNode)) {
            path.add(current);
            current = parentMap.get(current);
        }

        if (current != null && current.equals(startNode)) {
            path.add(startNode);
        }
        Collections.reverse(path);

        if (path.size() > 1) {
            path.removeFirst();
        }

        return path;
    }

    private static boolean isPassable(Node node, TypeObject objectType) {
        TypeObject nodeType = node.getType();
        // общие припятствия для всех 
        if (nodeType == TypeObject.ROCK || nodeType == TypeObject.TREE) {
            return false;
        }
        // для Herbivore избешать Predator
        if (objectType == TypeObject.PREDATOR && objectType == TypeObject.HERBIVORE) {
            return false;
        }
        // для Predator избегать Grass
        if (objectType == TypeObject.PREDATOR && nodeType == TypeObject.GRASS) {
            return false;
        }

        return true;
    }

}