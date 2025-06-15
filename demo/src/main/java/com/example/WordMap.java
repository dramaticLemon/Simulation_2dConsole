package com.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.conf.Config;
import com.example.conf.ConfigService;

public class  WordMap {
    // содержить в колекцию для хранения существ и их расположенияq
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
            System.out.println("Call MAP INNIT");
            instanse = new WordMap(config.getMapConfig().width, config.getMapConfig().heigth);
        }
        return instanse;
    }

    private void initializeGraph () {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = new Node(i, j);
                nodes[i][j] = node;
                emptyNodes.add(node); // добавить пустую ноду в список пустых нод
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

    // получить соседей
    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }
    // получить Node по координатам
    public Node getNodeAt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return nodes[y][x];
        }
        throw new IndexOutOfBoundsException("Invalid coordinate: " + x + ", " + y);
    }
    // проверка что клетка пустая
    public boolean isEmptyNode(Node node) {
        return node.getType().equals(TypeObject.EMPTY);
    }
    // получить двумерный массив с нодами
    public Node[][] getNodesMap() {
        return this.nodes;
    }
    
    // получить рандомную клетку
    public Node getRundomNode() {
        if (emptyNodes.isEmpty()) {
            throw new IllegalStateException("Нет пустых node");
        }
        Random random = new Random();
        return emptyNodes.get(random.nextInt(emptyNodes.size()));
    }

    // поменять тип ноды но не меняем привязку к клетке обьекта
    // для изменения списка пустых клеток
    public void setNodeType(Node node, TypeObject newType) {
        TypeObject oldType = node.getType(); // cохранить старый тип куска карты
        node.setType(newType); // поменять тип куска карты на новый
        if (oldType == TypeObject.EMPTY && newType != TypeObject.EMPTY) {
            emptyNodes.remove(node);
        } else if (oldType != TypeObject.EMPTY && newType == TypeObject.EMPTY) {
            emptyNodes.add(node);
        }
    }
}
