package view;

import java.util.Random;
import java.util.ArrayList;
import elements.Fruit;
import elements.Player;
import elements.Sprite;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameTimer extends AnimationTimer{
	private final static String BG_LINK = "/view/resources/GameStage_BG1.png";
	private GraphicsContext gc;
	private Scene scene;
	private long startSpawn;
	private Player player1;
	private ArrayList<Fruit> fruits;
	private Sprite bg;
	private Image bgImg;
	public boolean gravity = true;

	GameTimer(GraphicsContext gc, Scene scene) {
		this.gc = gc;
		this.scene = scene;
		startSpawn = System.nanoTime();

		player1 = new Player(0, 0);
		fruits = new ArrayList<Fruit>();
		bg = new Sprite(0, 0);

		setupBackground();
		keyPressEvent();
		spawnFruits(7);
	}

	// game loop
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		player1.frame();
		// renders fruits and baskets
		renderImages();
		// updates gravity
		gravity();
		// chat
	}

	// renders all elements
	private void renderImages() {
		bg.render(gc);
		player1.render(gc);
		for(Fruit f : fruits) {
			f.render(gc);
		}
	}

	// Gravity simulation for fruits
	private void gravity() {
		for(int i = 0; i < this.fruits.size(); i++) {
			Fruit f = this.fruits.get(i);

			if((f.getY() + f.getWidth()) < GameMenu.WINDOW_HEIGHT) {
				f.setY(f.getSpeed());
			} else {
				this.fruits.remove(i);
			}
		}
	}

	// sets-up background
	private void setupBackground() {
		bgImg = new Image(BG_LINK, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT, false, false);
		bg.loadImage(bgImg);
	}

	// spawns all fruits based on max numbers
	private void spawnFruits(int max) {
		Random r = new Random();
		Random type = new Random();

		for(int i = 0; i < max; i++) {
			double y = 0;
			double x = r.nextInt((int) (GameMenu.WINDOW_HEIGHT - 64));
			int t = type.nextInt(3);

			Fruit f = new Fruit(x, y, t);
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
