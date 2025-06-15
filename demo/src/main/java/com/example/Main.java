package com.example;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();

        EntityManager em = EntityManager.getInstance();
        System.out.println(em);
    }
}