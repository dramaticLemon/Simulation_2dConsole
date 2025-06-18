
package com.example;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Start simulation");
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation::startSimulation);
        simulationThread.start();

        Thread consoleThread = new Thread(new ConsoleHandler(simulation,scanner));
        consoleThread.setDaemon(true);
        consoleThread.start();
        logger.info("End simulation");
    }
}