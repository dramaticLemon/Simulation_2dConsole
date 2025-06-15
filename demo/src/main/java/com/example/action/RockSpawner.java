package com.example.action;

import java.util.function.Predicate;

import com.example.Node;
import com.example.Rock;
import com.example.WordMap;
import com.example.action.spawn_rules.Standart;
;


public class RockSpawner implements  SpawnAction{

    public static  void spawnInitAction(WordMap map, int count) {
        Predicate<Node> rule = new Standart();
        SpawnAction.spawnEntity(map, Rock::new, count, rule);
    }
}