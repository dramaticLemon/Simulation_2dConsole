package com.example.action.turn;

import com.example.Creatures;
import com.example.manage.EntityManager;

/**
 * Moved all creatures object
 */
public class MoveAction {
    private final EntityManager entityManager;

    public MoveAction(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void performMove() {
        for(Creatures object : entityManager.getAllCreatures() ) {
            object.makeMove();
        }
    }
}
