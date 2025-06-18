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
                    Доступные команды:
                    1 - Установить задержку 500мс
                    2 - Установить задержку 1000мс
                    3 - Установить задержку 2000мс
                    p - Пауза (остановить симуляцию)
                    f - Завершить симуляцию
                    h - Показать эту справку
                    """);
    }
}
