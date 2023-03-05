package com.koval.santontank1994;

import com.koval.santontank1994.entity.Bullet;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Brick {

    private int x;
    private int y;
    private static final int BRICK_SIZE = 32;
    private boolean destroyed = false;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        if (!destroyed) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, BRICK_SIZE, BRICK_SIZE);
        }
    }

    public boolean checkCollision(Tank tank) {
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.TANK_SIZE, Tank.TANK_SIZE);
        Rectangle brickRect = new Rectangle(x, y, BRICK_SIZE, BRICK_SIZE);
        if (tankRect.intersects(brickRect)) {
            tank.stop();
            return true;
        }
        return false;
    }

    public boolean checkCollision(Bullet bullet) {
        if (!destroyed) {
            Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), Bullet.BULLET_SIZE, Bullet.BULLET_SIZE);
            Rectangle brickRect = new Rectangle(x, y, BRICK_SIZE, BRICK_SIZE);
            if (bulletRect.intersects(brickRect)) {
                destroyed = true;
                return true;
            }
        }
        return false;
    }
}