package com.example;

import java.util.Scanner;

public class ConsoleHandler implements Runnable{
    private final Simulation simulation;
    private final Scanner scanner;

    public ConsoleHandler(Simulation simulation, Scanner scanner) {
        this.simulation = simulation;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Enter command: ");
            String command = scanner.next();
            switch (command) {
                case "1" -> simulation.setDelay(500);
                case "2" -> simulation.setDelay(1000);
                case "3" -> simulation.setDelay(2000);
                case "p" -> simulation.stop();
                case "f" -> simulation.finish();
                case "h" -> printHelp();
                default -> System.out.println("Uncnown command: Enter 'h'");
            }
        }

    }

    private void printHelp() {
    System.out.println("""
            Available commands:
            1 - Set delay to 500ms
            2 - Set delay to 1000ms
            3 - Set delay to 2000ms
            p - Pause (stop the simulation)
            f - Finish the simulation
            h - Show this help
            """);
    }
}
