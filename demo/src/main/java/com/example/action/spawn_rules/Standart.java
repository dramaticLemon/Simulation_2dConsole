package com.example.action.spawn_rules;

import java.util.function.Predicate;

import com.example.Node;

public class Standart implements Predicate<Node> {

    @Override
    public boolean test(Node node) {
        return true;
    }
}   
