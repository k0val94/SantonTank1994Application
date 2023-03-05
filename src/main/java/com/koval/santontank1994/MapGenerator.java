package com.koval.santontank1994;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapGenerator {

    int mapWidth = 1280 / BRICK_SIZE;
    int mapHeight = 1024 / BRICK_SIZE;

    private static final int BRICK_SIZE = 32;

    private static int[][] map;

    private BufferedImage brickImage;
    private BufferedImage tankImage;


    public MapGenerator() {
        map = new int[this.mapHeight][this.mapWidth];
        loadImages();
    }

    public MapGenerator(int mapWidth, int mapHeight) {
        map = new int[mapHeight][mapWidth];
        loadImages();
    }

    static void loadMap(String map_file) {
        InputStream in = MapGenerator.class.getClassLoader().getResourceAsStream(map_file);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int rows = Integer.parseInt(reader.readLine());
            int cols = Integer.parseInt(reader.readLine());

            map = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = reader.readLine();
                for (int j = 0; j < cols; j++) {
                    char c = line.charAt(j);
                    if (c == '#') {
                        map[i][j] = 1; // Brick
                    } else if (c == 'S' || c == 'A') {
                        map[i][j] = 2; // Tank
                    } else {
                        map[i][j] = 0; // Empty
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadImages() {
        try {
            brickImage = ImageIO.read(getClass().getClassLoader().getResource("images/brick.png"));
            tankImage = ImageIO.read(getClass().getClassLoader().getResource("images/tank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int x = j * BRICK_SIZE;
                int y = i * BRICK_SIZE;
                if (map[i][j] == 1) {
                    g.drawImage(brickImage, x, y, null);
                } else if (map[i][j] == 2) {
                    g.drawImage(tankImage, x, y, null);
                }
            }
        }
    }
}