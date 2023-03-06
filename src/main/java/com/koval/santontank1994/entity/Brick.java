package com.koval.santontank1994.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Brick extends MapObject {

    public static final int SIZE = 64;

    private boolean destroyed;

    public Brick(int x, int y) {
        super(x, y, "/images/brick_64.png");
        this.destroyed = false;
        this.boundingBox = new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics2D g) {
        if (!destroyed) {
            g.drawImage(getImage(), x, y, null);
        }
    }

    @Override
    public boolean checkCollision(MapObject obj) {
        if (!destroyed && obj instanceof MapObject) {
            return super.checkCollision(obj);
        }
        return false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void destroy() {
        setDestroyed(true);
        // Add additional logic to handle brick destruction
    }
}