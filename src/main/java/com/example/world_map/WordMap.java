package com.example.world_map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.conf.Config;
import com.example.conf.ConfigService;
import com.example.conf.TypeObject;

public class  WordMap {
    private static final Logger logger = LoggerFactory.getLogger(WordMap.class);
    private final int width;
    private final int height;
    private static WordMap instanse;
    private final Map<Node, List<Node>> adjList;
    private final Node[][] nodes;
    private final static Config config = ConfigService.get().getConfig(); 
    private final List<Node> emptyNodes = new ArrayList<>();

    private WordMap (int width, int heigth) {
        this.width = width;
        this.height = heigth;
        this.adjList = new HashMap<>();
        this.nodes = new Node[height][width];
        initializeGraph();
    }

    public static WordMap getInstance() {
        if (instanse == null) {
            logger.info("Call MAP INNIT");
            instanse = new WordMap(config.getMapConfig().width, config.getMapConfig().heigth);
        }
        return instanse;
    }

    private void initializeGraph () {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = new Node(i, j);
                nodes[i][j] = node;
                emptyNodes.add(node);
                adjList.put(node, new ArrayList<>());
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node currentNode = nodes[i][j];

                int[][] directions = {
                        {0, 1}, {0, - 1}, {1, 0}, {- 1, 0},
                        {1, 1}, {1, - 1}, {- 1, 1}, {- 1, - 1}
                };

                for (int[] dir : directions) {
                    int neighborX = j + dir[0];
                    int neighborY = i + dir[1];

                    if (isValidCoordinate(neighborX, neighborY)) {
                        Node neighborNode = nodes[neighborY][neighborX];
                        adjList.get(currentNode).add(neighborNode);
                    }
                }
            }
        }
    }

    private boolean isValidCoordinate (int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < height;
    }

    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Node getNodeAt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return nodes[y][x];
        }
        logger.error("Trying to get Node with invalid coordinates: {}, {}", x, y);
        throw new IndexOutOfBoundsException("Invalid coordinate: " + x + ", " + y);
    }

    public boolean isEmptyNode(Node node) {
        return node.getType().equals(TypeObject.EMPTY);
    }

    public Node[][] getNodesMap() {
        return this.nodes;
    }
    

    public Node getRundomNode() {
        if (emptyNodes.isEmpty()) {
            logger.error("not find empty nodes");
            throw new IllegalStateException("Нет пустых node");
        }
        Random random = new Random();
        return emptyNodes.get(random.nextInt(emptyNodes.size()));
    }
    /**
     * change type node but not change bind node->Class object
     * @param node
     * @param newType
     */
    public void setNodeType(Node node, TypeObject newType) {
        TypeObject oldType = node.getType(); // save ols type node map
        node.setType(newType); // change type node map
        if (oldType == TypeObject.EMPTY && newType != TypeObject.EMPTY) {
            emptyNodes.remove(node);
        } else if (oldType != TypeObject.EMPTY && newType == TypeObject.EMPTY) {
            emptyNodes.add(node);
        }
    }
}
