package com.example.action;

import com.example.action.turn.MoveAction;
import com.example.action.turn.SpawnAction;

public class TurnAction {
    private final MoveAction moveAction;
    private final SpawnAction spawnAction;

    public TurnAction(MoveAction moveAction, SpawnAction spawnAction) {
        this.moveAction = moveAction;
        this.spawnAction = spawnAction;
    }

    public void performTurnMove() {
        moveAction.performMove();
    }

    public void performSpawn() {
        spawnAction.performSpawn();
    }

}
