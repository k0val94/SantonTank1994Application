package com.koval.santontank1994;

import com.koval.santontank1994.game.Game;
import com.koval.santontank1994.config.GameConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GameConfig.class)
public class SantonTank1994Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(SantonTank1994Application.class).headless(false).run(args);

		Game game = context.getBean(Game.class);
		game.play();
	}
}