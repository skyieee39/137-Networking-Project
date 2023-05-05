package elements;

import javafx.scene.image.Image;

public class Player extends Sprite {
	private String name;
	private int score;
	private boolean alive;

	public final static Image PLAYER_IMAGE = new Image ("/model/resources/BUTTON_RELEASE.png", 128, 128, false, false);

	public Player(int xPos, int yPos) {
		super(xPos, yPos);
		this.name = "Player";
		this.score = 0;
		this.alive = true;
		}

	public void move() {
		this.x += 5;
		this.y += 5;
	}

	public boolean isAlive() {
		if(this.alive) return true;
		return false;
	}

	public String getName() {
		return this.name;
	}


}