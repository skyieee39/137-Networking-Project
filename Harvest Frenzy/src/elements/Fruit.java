package elements;

import javafx.scene.image.Image;

public class Fruit extends Sprite {
	public static final int MAX_FRUIT_SPEED = 5;
	public final static Image FRUIT_IMAGE = new Image ("/model/resources/Cart_Basket.png", 64, 64, false, false);
	private int speed;
	private boolean alive;
	
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
