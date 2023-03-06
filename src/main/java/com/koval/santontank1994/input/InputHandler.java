package com.koval.santontank1994.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import com.koval.santontank1994.util.SoundPlayer;
import com.koval.santontank1994.entity.Tank;
import lombok.Getter;

@Getter
public class InputHandler extends KeyAdapter {

    private static final boolean[] keys = new boolean[256];
    private Tank tank;
    private SoundPlayer sound;

    public InputHandler(Tank tank) {

        this.tank = tank;
        try {
            URL soundURL = getClass().getResource("/audio/tank_sound.wav");
            if (soundURL == null) {
                throw new RuntimeException("Unable to load sound file: /audio/tank_sound.wav");
            }
            sound = new SoundPlayer(soundURL.toString());
        } catch (Exception e) {
            System.err.println("Error loading sound file: " + e.getMessage());
        }
    }

    public static boolean isKeyDown(int keyCode) {
        return keys[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == tank.getUpKey()) {
            tank.setVy(-5);
            tank.setDirection(Tank.UP);
        } else if (e.getKeyCode() == tank.getDownKey()) {
            tank.setVy(5);
            tank.setDirection(Tank.DOWN);
        } else if (e.getKeyCode() == tank.getLeftKey()) {
            tank.setVx(-5);
            tank.setDirection(Tank.LEFT);
        } else if (e.getKeyCode() == tank.getRightKey()) {
            tank.setVx(5);
            tank.setDirection(Tank.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            sound.play();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        if (e.getKeyCode() == tank.getUpKey() || e.getKeyCode() == tank.getDownKey()) {
            tank.setVy(0);
        } else if (e.getKeyCode() == tank.getLeftKey() || e.getKeyCode() == tank.getRightKey()) {
            tank.setVx(0);
        }
    }

}