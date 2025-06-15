package com.example;

public abstract class Entity {
    private Node currentNode;


    // привязка
    void attachToNode(Node node) {
        this.currentNode = node;
    };

    // отвязка
    void detachNode() {
        this.currentNode = null;
    }

    public Node getCurrentNode() {
        return this.currentNode;
    }



    

}
