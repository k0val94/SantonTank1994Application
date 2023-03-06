package com.koval.santontank1994.game;

import com.koval.santontank1994.entity.MapObject;
import com.koval.santontank1994.entity.Tank;
import com.koval.santontank1994.input.InputHandler;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class Game extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 1024;
    private static final int GRID_SIZE = 64;
    private static final int MAP_WIDTH = GAME_WIDTH / GRID_SIZE;
    private static final int MAP_HEIGHT = GAME_HEIGHT / GRID_SIZE;

    private static final String MAP_FILE = "resources/maps/hochhaus.map";

    private List<Tank> tanks;
    private MapGenerator mapGenerator;

    private BufferedImage offscreenImage;
    private Graphics2D offscreenGraphics;

    public Game() {
        setTitle("Santon Tank 1994");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Level level = new Level(MAP_FILE);
        mapGenerator = new MapGenerator(level.getMapData());

        tanks = level.getTanks();

        offscreenImage = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        offscreenGraphics = offscreenImage.createGraphics();
    }

    public void play() {
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
        // Update movements of tanks
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
        // Draw background
        offscreenGraphics.setColor(Color.GRAY);
        offscreenGraphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Draw map
        mapGenerator.draw(offscreenGraphics);

        // Draw tanks
        for (Tank tank : tanks) {
            tank.draw(offscreenGraphics);
        }

        // Draw offscreen image on window
        getGraphics().drawImage(offscreenImage, 0, 0, null);
    }
}