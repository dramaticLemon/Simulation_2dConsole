package com.example.world_map;

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

import com.example.conf.TypeObject;

public class Search {
     /**
     * Breadth first search with return Nodes list path
     * @param value Value node
     * @param startNode Current starter link
     * @param map Rendering map
     * @return Nodes list path
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
        Set<Node> alreadyVisited = new HashSet<>();// local set from tracking visites nodes
        Map<Node, Node> parentMap = new HashMap<>(); // storage parent nodesa

        // add started node in queue and mark <visited>
        queue.add(startNode);
        alreadyVisited.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();

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
     * @param parentMap Map,key - node, value - him parrant в BFS
     * @param startNode Started node path
     * @param endNode Finaly node path
     * @return List nodes
     */
    private static List<Node> reconstructionPath(Map<Node, Node> parentMap, Node startNode, Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

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
        // common obstacles for each
        if (nodeType == TypeObject.ROCK || nodeType == TypeObject.TREE) {
            return false;
        }
        // for Herbivore
        if (objectType == TypeObject.PREDATOR && objectType == TypeObject.HERBIVORE) {
            return false;
        }
        // for Predator
        if (objectType == TypeObject.PREDATOR && nodeType == TypeObject.GRASS) {
            return false;
        }

        return true;
    }

}