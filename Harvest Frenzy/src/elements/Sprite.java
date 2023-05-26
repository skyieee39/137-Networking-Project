package elements;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image img; // image of the sprite
	protected double x, y; // x, y positions of the sprite
	protected boolean visible; // flag if to render or not
	protected double width;
	protected double height;

	// === CONSTRUCTOR ===
	public Sprite(double xPos, double yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}

	// method to load image
	public void loadImage(Image img){
		try{
			this.img = img;
	        setSize();
	        } catch(Exception e){
	        	System.out.print(e);
	        	System.exit(1);
	        }
	}

	// method to set the image to the image view node
	public void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
<<<<<<< HEAD
		}
	
	// method to set the object's width and height properties
	private void setSize(){
			this.width = this.img.getWidth();
			this.height = this.img.getHeight();
=======
>>>>>>> 9dc27120b7068890a3582071381c25dbecca15ae
	}

	// method that will check for collision of two sprites
	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}

	// method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}

	// method to return the image
	Image getImage(){
		return this.img;
	}

	// getters
	// method to get x position
	public double getX() {
    	return this.x;
	}

	// method to get y position
	public double getY() {
    	return this.y;
	}

	public double getWidth() {
		return width;
	}

	// method to get visible property
	public boolean getVisible(){
		return visible;
	}

	// method to check if visible or not
	public boolean isVisible(){
		if(visible) return true;
		return false;
	}

	// setters
	// sets x position
	public void setX(double newX){
		this.x += newX;
	}

	// sets y position
	public void setY(double newY){
		this.y += newY;
	}

	// sets sprite width
	public void setWidth(double val){
		this.width = val;
	}

	// sets sprite height
	public void setHeight(double val){
		this.height = val;
	}

	// sets visible property
	public void setVisible(boolean value){
		this.visible = value;
	}

}