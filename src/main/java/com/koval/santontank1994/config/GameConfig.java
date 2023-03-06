package com.koval.santontank1994.config;

import com.koval.santontank1994.game.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.GraphicsEnvironment;

@Configuration
public class GameConfig {

    @Bean
    public Game game() {
        if (GraphicsEnvironment.isHeadless()) {
            throw new RuntimeException("Cannot run game in headless environment");
        }
        return new Game();
    }
}