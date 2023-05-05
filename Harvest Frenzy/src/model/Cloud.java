package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GameMenu;

public class Cloud {
	private double velocity;
	private Image image;
	private boolean alive;
	protected double x, y;

	public Cloud(Image image, double velocity, double x, double y) {
		this.velocity = velocity;
		this.image = image;
		this.alive = true;
		this.x = x;
		this.y = y;
		if(this.x < GameMenu.WINDOW_WIDTH){
			this.alive = true;
		} else {
			this.alive = false;
		}
	}

	public void render(GraphicsContext gc){
		gc.drawImage(this.image, this.x, this.y);
    }

	public void move() {
		if(this.alive){
			if(this.x <= GameMenu.WINDOW_WIDTH){
				this.x += this.velocity;
			} else {
				this.alive = false;
			}
		} else {
			this.x = 0 - GameMenu.WINDOW_WIDTH;
			this.alive = true;
		}
	}
}
