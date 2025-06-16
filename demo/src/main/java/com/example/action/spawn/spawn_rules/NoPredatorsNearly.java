package com.example.action.spawn.spawn_rules;

import java.util.Objects;
import java.util.function.Predicate;

import com.example.entity.Predator;
import com.example.world_map.Node;
import com.example.world_map.WordMap;

public class NoPredatorsNearly implements Predicate<Node> {
    private final WordMap map;

    public NoPredatorsNearly(WordMap map) {
    this.map = map;
    }

    @Override
    public boolean test(Node node) {
        return map.getNeighbors(node).stream()
            .map(Node::getObjectLink)
            .filter(Objects::nonNull)
            .noneMatch(obj -> obj instanceof Predator);
    }
}

