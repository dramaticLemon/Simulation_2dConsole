package com.example.action.spawn.spawn_rules;

import java.util.function.Predicate;

import com.example.world_map.Node;

public class Standart implements Predicate<Node> {

    @Override
    public boolean test(Node node) {
        return true;
    }
}   
