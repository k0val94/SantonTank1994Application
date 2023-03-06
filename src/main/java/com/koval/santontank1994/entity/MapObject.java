package com.koval.santontank1994.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class MapObject {
    protected int x, y;
    protected Rectangle boundingBox;
    protected BufferedImage image;

    public MapObject(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(x, y);
        setImage(imagePath);
    }

    public abstract void draw(Graphics2D g);

    public void update() {
        boundingBox.setLocation(x, y);
    }

    public boolean checkCollision(MapObject obj) {
        return boundingBox.intersects(obj.boundingBox);
    }

    protected void setImage(String path) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getImage() {
        return image;
    }
}