package view;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import app.SecTimer;
import elements.Fruit;
import elements.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameTimer extends AnimationTimer{
	private GraphicsContext gc;
	private Scene scene;
	private long startSpawn;
	private Player player1;
	private ArrayList<Fruit> fruits;
	private GameStage gs;
	public boolean gravity = true;

	GameTimer(GraphicsContext gc, Scene scene, GameStage gs) {
		this.gc = gc;
		this.scene = scene;
		this.gs = gs;
		startSpawn = System.nanoTime();

		player1 = new Player(0, GameMenu.WINDOW_HEIGHT - 64);
		fruits = new ArrayList<Fruit>();

		keyPressEvent();
		spawnFruits(7);
	}

	// game loop
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		long startTime = TimeUnit.NANOSECONDS.toSeconds(startSpawn);
		long currentTime = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);

		// computes for the time passed after starting
		int durationTime = (int)(currentTime - startTime);
		player1.frame();
		// renders fruits and baskets
		this.renderImages();

		// updates gravity
		this.gravity();
	}

	// renders all elements
	public void renderImages() {
		this.player1.render(this.gc);

		for(Fruit f : this.fruits) {
			f.render(this.gc);
		}
	}

	// adds value to y axis ng mga elements to simulate gravity
	private void gravity() {
		// for each fruit, dagdag sa y axis
		for(int i = 0; i < this.fruits.size(); i++) {
			Fruit f = this.fruits.get(i);

			if((f.getY() + 64) < GameMenu.WINDOW_HEIGHT) {
				Random r = new Random();

				int speed = r.nextInt((int) Fruit.MAX_FRUIT_SPEED);

				f.setY(speed);
			}
		}
	}

	// spawns all fruits based on max numbers
	private void spawnFruits(int max) {
		Random r = new Random();

		for(int i = 0; i < max; i++) {
			double y = 0;
			double x = r.nextInt((int) (GameMenu.WINDOW_HEIGHT - 64));

			Fruit f = new Fruit(x, y);
			this.fruits.add(f);
		}
	}

	private void keyPressEvent() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				// Will ignore any space key input while the player is currently in the jumping state
				if (!(e.getCode().equals(KeyCode.SPACE) && player1.getIsJumping())) {
					player1.move(e.getCode());
				}

			}
		});

		this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				player1.halt(e.getCode());
			}
		});
	}
}
