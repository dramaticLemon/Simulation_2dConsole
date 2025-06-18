package com.example;

import com.example.world_map.Node;

public abstract class Entity {
    private Node currentNode;

    // привязка
    public void attachToNode(Node node) {
        this.currentNode = node;
    };

    // отвязка
    public void detachNode() {
        this.currentNode = null;
    }

    public Node getCurrentNode() {
        return this.currentNode;
    }



    

}
