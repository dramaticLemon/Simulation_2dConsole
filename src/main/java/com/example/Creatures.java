package com.example;

public abstract class Creatures extends Entity{
    protected int speed;
    protected int HP;
    protected boolean alived = true;
    public abstract void makeMove();
    public abstract void changeHP(int value);

    public boolean isAlive() {
        return this.alived;
    }
    
}
