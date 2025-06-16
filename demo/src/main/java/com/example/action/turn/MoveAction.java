package com.example.action.turn;

import com.example.Creatures;
import com.example.manage.EntityManager;


// передвинуть все обьекты которые могут ходить
public class MoveAction {
    private final EntityManager entityManager;

    public MoveAction(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void performMove() {
        for(Creatures object: entityManager.getAllCreatures() ) {
            object.makeMove();
        }
    }
}
