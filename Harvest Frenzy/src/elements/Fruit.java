package elements;

import java.util.Random;

import javafx.scene.image.Image;
import view.GameMenu;

public class Fruit extends Sprite {
	private static final int MIN_SPEED = 2;
	private double speed;
	private int points;
	private int type;
	private boolean alive;
	private Image fruitimg;
	public Fruit(double x, double y, int type){
		super(x, y);
		this.alive = true;
		this.type = type;
		fruitConfig();
		setHeight(64);
		setWidth(64);
	}
	private void fruitConfig() {
		if (type == 0) {
			fruitimg = new Image ("/model/resources/sprites/fruits/pineapple.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/300) + MIN_SPEED;
			points = 10;
		} else if (type == 1) {
			fruitimg = new Image ("/model/resources/sprites/fruits/banana.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/200) + MIN_SPEED;
			points = 5;
		} else if (type == 2) {
			fruitimg = new Image ("/model/resources/sprites/fruits/strawberry.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/150) + MIN_SPEED;
			points = 1;
		}

		loadImage(fruitimg);
	}

	public boolean collideWithPlayer(Player player) {
		if (collidesWith(player)) {
			player.setScore(points);
			setIsDead();
			return true;
		}
		return false;
	}

	// checks if the fruit is alive
	public boolean isAlive() {
		return this.alive;
	}

	public double getSpeed() {
		return speed;
	}

	// sets if the fruit is dead or alive
	public void setIsDead(){
		this.alive = false;
		setVisible(false);
	}
}
