package com.koval.santontank1994;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tank {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int TANK_SIZE = 20;

    private int x, y, vx, vy, direction, upKey, downKey, leftKey, rightKey;

    private BufferedImage image;

    private Rectangle boundingBox;

    public Tank(int x, int y, int upKey, int downKey, int leftKey, int rightKey) {
        this.x = x;
        this.y = y;
        this.direction = UP;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/textures/tank_up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boundingBox = new Rectangle(x, y, 32, 32);
    }

    public Tank(int x, int y) {
    }

    public void update() {
        x += vx;
        y += vy;
        boundingBox.setLocation(x, y);
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, null);
    }

    public void setDirection(int direction) {
        this.direction = direction;

        try {
            switch (direction) {
                case UP:
                    image = ImageIO.read(getClass().getResourceAsStream("/textures/tank_up.png"));
                    break;
                case DOWN:
                    image = ImageIO.read(getClass().getResourceAsStream("/textures/tank_down.png"));
                    break;
                case LEFT:
                    image = ImageIO.read(getClass().getResourceAsStream("/textures/tank_left.png"));
                    break;
                case RIGHT:
                    image = ImageIO.read(getClass().getResourceAsStream("/textures/tank_right.png"));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVelocity(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void stop() {
        vx = 0;
        vy = 0;
    }
}