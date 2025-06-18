
package com.example;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation::startSimulation);
        simulationThread.start();

        Thread consoleThread = new Thread(new ConsoleHandler(simulation,scanner));
        consoleThread.setDaemon(true);
        consoleThread.start();

    }
}