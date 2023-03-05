package com.koval.santontank1994;

import org.springframework.boot.SpringApplication;

import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Game extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 1024;
    private static final int GRID_SIZE = 32;
    private static final int MAP_WIDTH = GAME_WIDTH / GRID_SIZE;
    private static final int MAP_HEIGHT = GAME_HEIGHT / GRID_SIZE;

    private static final String MAP_FILE = "resources/maps/test.map";

    private Tank[] tanks;
    private MapGenerator mapGenerator;

    private BufferedImage offscreenImage;
    private Graphics2D offscreenGraphics;



    public void play() {
        setTitle("Santon Tank 1994");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tanks = new Tank[2];
        tanks[0] = new Tank(64, GAME_HEIGHT - 96);
        tanks[0].setDirection(Tank.DOWN);
        tanks[1] = new Tank(GAME_WIDTH - 96, 64);
        tanks[1].setDirection(Tank.LEFT);

        Level level = new Level(MAP_FILE);

        level.createBufferedImage(MAP_WIDTH, MAP_HEIGHT);

        setVisible(true);
        startGameLoop();
    }

    private void startGameLoop() {
        while (true) {
            update();
            render();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Bewegungen der Tanks aktualisieren
        for (Tank tank : tanks) {
            int vx = 0, vy = 0;

            if (InputHandler.isKeyDown(tank.getUpKey())) {
                vy = -5;
                tank.setDirection(Tank.UP);
            }
            if (InputHandler.isKeyDown(tank.getLeftKey())) {
                vx = -5;
                tank.setDirection(Tank.LEFT);
            }
            if (InputHandler.isKeyDown(tank.getDownKey())) {
                vy = 5;
                tank.setDirection(Tank.DOWN);
            }
            if (InputHandler.isKeyDown(tank.getRightKey())) {
                vx = 5;
                tank.setDirection(Tank.RIGHT);
            }

            tank.setVelocity(vx, vy);
            tank.update();
        }
    }

    private void render() {
        // Hintergrund zeichnen
        offscreenGraphics.setColor(Color.GRAY);
        offscreenGraphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Karte zeichnen
        mapGenerator.draw(offscreenGraphics);

        // Tanks zeichnen
        for (Tank tank : tanks) {
            tank.draw(offscreenGraphics);
        }

        // Offscreen-Bild auf das Fenster zeichnen
        getGraphics().drawImage(offscreenImage, 0, 0, null);
    }
}