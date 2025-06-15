package com.example.action;

import java.util.function.Predicate;

import com.example.Herbivore;
import com.example.Node;
import com.example.WordMap;
import com.example.action.spawn_rules.NoPredatorsNearly;

public class HarbivoreSpawner implements  SpawnAction{

    public static  void spawnInitAction(WordMap map, int count) {
        Predicate<Node> rule = new NoPredatorsNearly(map);
        SpawnAction.spawnEntity(map, Herbivore::new, count, rule);
    }
}
