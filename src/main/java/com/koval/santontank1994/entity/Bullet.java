package com.koval.santontank1994.entity;

import java.awt.Rectangle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet {

    public static final int BULLET_SIZE = 2;
    private static final int SPEED = 5;

    private int x, y;
    private int direction;
    private boolean active;
    private Rectangle boundingBox;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.active = true;
        this.boundingBox = new Rectangle(x, y, 8, 8);
    }

    public void move() {
        switch (direction) {
            case Tank.UP:
                y -= SPEED;
                break;
            case Tank.DOWN:
                y += SPEED;
                break;
            case Tank.LEFT:
                x -= SPEED;
                break;
            case Tank.RIGHT:
                x += SPEED;
                break;
        }

        boundingBox.setLocation(x, y);
    }
}