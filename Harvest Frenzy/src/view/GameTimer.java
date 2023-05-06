package view;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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
	
	GameTimer(GraphicsContext gc, Scene scene) {
		this.gc = gc;
		this.scene = scene;
		this.startSpawn = System.nanoTime();
		
		this.player1 = new Player(0, 0);
		this.fruits = new ArrayList<Fruit>();
		
		this.spawnFruits(7);
		
		this.handleKeyPressEvent();
	}
	
	// handles key press events
	private void handleKeyPressEvent() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode keyCode = e.getCode();
				movePlayer(keyCode);
			}
		});
	}
	
	// game loop
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		long startTime = TimeUnit.NANOSECONDS.toSeconds(startSpawn);
		long currentTime = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
		
		// computes for the time passed after starting
		int durationTime = (int)(currentTime - startTime); 
		
		// renders fruits and baskets
		this.renderImages();
		
		// updates gravity
		this.gravity();
	}
	
	// moves player based on key input
	private void movePlayer(KeyCode keyCode) {
		// pwede baguhin lahat ng values, experiment lang
		switch(keyCode) {
		case UP:
			this.player1.setY(-384);
			break;
		case DOWN:
			break;
		case RIGHT:
			this.player1.setX(128);
			break;
		case LEFT:
			this.player1.setX(-128);
			break;
		case ESCAPE:
			System.exit(0);
		default:
			break;
			}
		
		this.renderImages();
		System.out.println(keyCode + " key pressed.");
		System.out.println("X: " + this.player1.getX());
		System.out.println("Y: " + this.player1.getY());
	}
	
	// renders all fruits and da player
	public void renderImages() {
		this.player1.render(this.gc);
		
		for(Fruit f : this.fruits) {
			f.render(this.gc);
		}
	}
	
	// adds value to y axis ng mga elements to simulate gravity
	private void gravity() {
		if((this.player1.getY() + 64) < GameMenu.WINDOW_HEIGHT) {
			this.player1.setY(4);
		}
		
		// the next two if-statements teleports the basket to the other side
		// if sumobra na sa window
		if((this.player1.getX() + 64) > GameMenu.WINDOW_WIDTH) {
			this.player1.setX(-GameMenu.WINDOW_WIDTH);
		}
		
		if((this.player1.getX()) < 0) {
			this.player1.setX(GameMenu.WINDOW_WIDTH);
		}
		
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
}
