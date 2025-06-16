package com.example;

public abstract class Creatures extends Entity{
    // скорость
    protected int HP;
    public abstract void makeMove();
    public abstract void changeHP(int value);
}
