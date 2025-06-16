package com.example.world_map;

import com.example.conf.Config;
import com.example.conf.ConfigService;

public class RenderMap implements Render{
    private final WordMap map = WordMap.getInstance(); // sigletone
    private final Config config = ConfigService.get().getConfig(); //singleton

    @Override
    public void render() {
        Node[][] nodes = map.getNodesMap();
        int cellWidth = 5;

        renderTopBorders(cellWidth); 
        rederBody(cellWidth, nodes);
        renderButtom(cellWidth);
        
    }
    private void renderTopBorders(int cellWidth) {
        System.out.print("╔");
        for (int x = 0; x < config.getMapConfig().width; x++) {
            for (int i = 0; i < cellWidth; i++) {
                System.out.print("═");
            }
            if (x < config.getMapConfig().width - 1) {
                System.out.print("╦");
            }
        }
        System.out.println("╗");
    }

    private void rederBody(int cellWidth, Node[][] nodes) {
        for (int y = 0; y < config.getMapConfig().heigth; y++) {
            System.out.print("║"); 
            for (int x = 0; x < config.getMapConfig().width; x++) { 
                Node node = nodes[y][x];
                String symbol = node.getType().getSymbol();
                System.out.printf("%" + (-cellWidth) + "s", String.format("%" + (cellWidth - (cellWidth - symbol.length()) / 2) + "s", symbol));

                if (x < config.getMapConfig().width - 1) {
                    System.out.print("║");
                }
            }
            System.out.println("║");


            if (y < config.getMapConfig().heigth - 1) {
                System.out.print("╠"); 
                for (int x = 0; x < config.getMapConfig().width; x++) {
                    for (int i = 0; i < cellWidth; i++) {
                        System.out.print("═");
                    }
                    if (x < config.getMapConfig().width - 1) {
                        System.out.print("╬");
                    }
                }
                System.out.println("╣");
            }
        }
    }

    private void renderButtom(int cellWidth) {
        System.out.print("╚"); 
        for (int x = 0; x < config.getMapConfig().width; x++) {
            for (int i = 0; i < cellWidth; i++) {
                System.out.print("═");
            }
            if (x < config.getMapConfig().width - 1) { 
                System.out.print("╩");
            }
        }
        System.out.println("╝");
        System.out.println();
    }
    
}
