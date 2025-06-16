
package com.example;
public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation::startSimulation);
        simulationThread.start();

        Thread consoleThread = new Thread(new ConsoleHandler(simulation));
        consoleThread.setDaemon(true);
        consoleThread.start();

    }
}