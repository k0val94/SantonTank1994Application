package com.koval.santontank1994;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Level {
    public BufferedImage offscreenImage;
    private List<String> map = new ArrayList<>();

    public Level(String mapFile) {
        MapGenerator.loadMap(mapFile);
    }

    public BufferedImage createBufferedImage(int GAME_WIDTH, int GAME_HEIGHT) {
        offscreenImage = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        offscreenImage.createGraphics();
        return offscreenImage;
    }

    public List<String> getMap() {
        return map;
    }
}