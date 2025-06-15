package com.example.action;

import java.util.function.Predicate;

import com.example.Node;
import com.example.Tree;
import com.example.WordMap;
import com.example.action.spawn_rules.Standart;;

public class TreeSpawner implements SpawnAction{

    public static void spawnInitAction(WordMap map, int count) {
        Predicate<Node> rule = new Standart();
        SpawnAction.spawnEntity(map, Tree::new, count, rule);
    }
}
