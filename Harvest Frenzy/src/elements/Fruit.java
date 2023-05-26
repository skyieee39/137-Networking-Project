package elements;

import java.util.Random;
import view.GameMenu;

import javafx.scene.image.Image;

public class Fruit extends Sprite {
	public static final int MAX_FRUIT_SPEED = 5;
	public final static Image FRUIT_IMAGE = new Image ("/model/resources/Cart_Basket.png", 64, 64, false, false);
	private static final int MIN_SPEED = 2;
	private double speed;
	private int type;
	private boolean alive;
	private Image fruitimg;
	
	public Fruit(double x, double y, int type){
		super(x, y);
		this.speed = Fruit.MAX_FRUIT_SPEED;
		this.loadImage(FRUIT_IMAGE);
		
		this.type = type;
		fruitConfig();
	}
	
	private void fruitConfig() {
		if (type == 0) {
			fruitimg = new Image ("/model/resources/sprites/fruits/pineapple.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/300 - MIN_SPEED) + MIN_SPEED;
		} else if (type == 1) {
			fruitimg = new Image ("/model/resources/sprites/fruits/banana.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/200 - MIN_SPEED) + MIN_SPEED;
		} else if (type == 2) {
			fruitimg = new Image ("/model/resources/sprites/fruits/strawberry.png", 64, 64, false, false);
			Random r = new Random();
			speed = r.nextInt((int) GameMenu.WINDOW_HEIGHT/150 - MIN_SPEED) + MIN_SPEED;
		}

		loadImage(fruitimg);
	}

	
	public Fruit(double x, double y){
		super(x, y);
		this.speed = Fruit.MAX_FRUIT_SPEED;
		this.loadImage(FRUIT_IMAGE);
		this.alive = true;
	}

	private void setY() {
		this.setY(MAX_FRUIT_SPEED);
	}
	
	// checks if the fruit is alive
	public boolean isAlive() {
		return this.alive;
	}

	// sets if the fruit is dead or alive
	public void setIsDead(){
		this.alive = false;
	}
}
