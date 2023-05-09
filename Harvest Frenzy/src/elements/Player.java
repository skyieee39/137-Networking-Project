package elements;

import javafx.scene.image.Image;

public class Player extends Sprite {
	private String name;
	private int score;
	private boolean alive;
	private double veloX = 0;
	private double veloY = 0;

	public final static Image PLAYER_IMAGE = new Image ("/model/resources/player_placeholder.png", 64, 64, false, false);

	public Player(double xPos, double yPos) {
		super(xPos, yPos);
		this.loadImage(PLAYER_IMAGE);
		this.name = "Player";
		this.score = 0;
		this.alive = true;
		}

	// THIS FUNCTION IS CALLED EVERY TIME THE HANDLE IS CALLED
	public void frame() {
		x += veloX;
		y += veloY;
	}

	public boolean isAlive() {
		if(this.alive) return true;
		return false;
	}

	public String getName() {
		return this.name;
	}

	public void setVeloX(double vx) {
		veloX = vx;
	}
	public void setVeloY(double vy) {
		veloY = vy;
	}


}