package com.koval.santontank1994;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SantonTank1994Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(SantonTank1994Application.class).headless(false).run(args);

		Game game = context.getBean(Game.class);
		game.play();
	}

}