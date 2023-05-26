package elements;

import java.util.Random;

import javafx.scene.image.Image;
import view.GameMenu;

public class Fruit extends Sprite {
<<<<<<< HEAD
	public static final int MAX_FRUIT_SPEED = 5;
	public final static Image FRUIT_IMAGE = new Image ("/model/resources/Cart_Basket.png", 64, 64, false, false);
=======
	private static final int MIN_SPEED = 2;
	private double speed;
	private int type;
>>>>>>> 9dc27120b7068890a3582071381c25dbecca15ae
	private boolean alive;
	private Image fruitimg;
	public Fruit(double x, double y, int type){
		super(x, y);
		this.alive = true;
<<<<<<< HEAD
		this.loadImage(FRUIT_IMAGE);
		
=======
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
>>>>>>> 9dc27120b7068890a3582071381c25dbecca15ae
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
		this.setVisible(false);
		System.out.println("setting visibilty to false");
		this.alive = false;
	}
}
