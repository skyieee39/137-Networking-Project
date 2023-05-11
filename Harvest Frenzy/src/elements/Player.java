package elements;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import view.GameMenu;

public class Player extends Sprite {
	public final static Image PLAYER_IMAGE = new Image ("/model/resources/player_placeholder.png", 64, 64, false, false);
	public final static int MAX_JUMP_COUNT = 2;
	private String name;
	private int score;
	private boolean alive;
	private double veloX = 0;
	private double veloY = 0;

	private boolean playerGravity = true;
	private boolean isJumping = false;
	private boolean keyJumpLock = false;
	private int jumpLockCounter = 0;
	private int jumpCount = 0;

	// === Constructor ===
	public Player(double xPos, double yPos) {
		super(xPos, yPos);
		loadImage(PLAYER_IMAGE);
		name = "Player";
		score = 0;
		alive = true;
	}

	// THIS FUNCTION IS CALLED EVERY TIME THE HANDLE IS CALLED
	public void frame() {
		x += veloX;
		y += veloY;
		jumpLockCounter();
		gravity();
	}

	// === Player movement ===
	// moves player based on key input
	public void move(KeyCode keyCode) {
		switch(keyCode) {
			case SPACE:
				if (!getJumpLock()) {
					isJumping = true;		// Will let the key listener know to not register other space key inputs
					setJumpLock(true);		// Will let the jumpLockCounter know to perform a jump
					playerGravity = false;	// Makes sure that the jumping distance is maximized without the effect of gravity
					jumpLockCounter();
				}
				break;
			case RIGHT:
				setVeloX(5);
				break;
			case LEFT:
				setVeloX(-5);
				break;
			default:
				break;
				}

			System.out.println(keyCode + " key pressed.");
			System.out.println("X: " + getX());
			System.out.println("Y: " + getY());
		}

	public void halt(KeyCode keyCode) {
		switch(keyCode) {
			case SPACE:
				playerGravity = true;
				break;
			case RIGHT:
				setVeloX(0);
				break;
			case LEFT:
				setVeloX(0);
				break;
			case ESCAPE:
				System.exit(0);
			default:
				break;
			}

			System.out.println(keyCode + " key released.");
			System.out.println("X: " + getX());
			System.out.println("Y: " + getY());
		}

	// === Physics ===
	private void gravity() {
		if((getY() + 64) < GameMenu.WINDOW_HEIGHT) {
			if(playerGravity) {
				setVeloY(5);
			}
		} else {
			// player is touching the ground
			setJumpLock(false);
			isJumping = false;
			setVeloY(0);
			// Will only reset jump count to 0 once the player touches the ground
			if (jumpCount >= MAX_JUMP_COUNT) {
				jumpCount = 0;
			}
		}

		// Teleport the player to the other side once it reaches the end of the window
		if((getX() + 64) > GameMenu.WINDOW_WIDTH) {
			setX(-GameMenu.WINDOW_WIDTH);
		}
		if((getX()) < 0) {
			setX(GameMenu.WINDOW_WIDTH);
		}
	}

	// === Jump Lock functions ===
	private boolean getJumpLock(){
		return keyJumpLock;
	}

	public boolean getIsJumping() {
		return isJumping;
	}

	private void setJumpLock(boolean lock) {
		keyJumpLock = lock;
	}

	private void jumpLockCounter() {
		if(getJumpLock()) {
			if (jumpCount < MAX_JUMP_COUNT) { // if there are jumps left
				System.out.println("JLC: "+jumpLockCounter);
				jumpLockCounter++;
				// This allows the player to jump up to 160 units up per jump
				if (jumpLockCounter < 8) {
					setVeloY(-20);		// Sets jump distance to 20 units up per frame until 8 frames
					isJumping = true;	// Will let the key listener know to not register other space key inputs
				} else {				// The maximum height is now reached
					setVeloY(0);
					playerGravity = true;	// Gravity will be turned on again
				}
				if (jumpLockCounter > 12) {	// 12 frames has passed (1 jump animation)
					jumpCount++;			// Increment the number of jumps performed
					isJumping = false;		// Tell the key listener that it can now accept another jump key input
					setJumpLock(false);		// Tell this function to not do anything
					jumpLockCounter = 0;	// reset the jump action counter
				}
			}
		}
	}

	// === Getters ===
	public boolean isAlive() {
		return alive;
	}
	public String getName() {
		return name;
	}

	// === Setters ===
	public void setVeloX(double vx) {
		veloX = vx;
	}
	public void setVeloY(double vy) {
		veloY = vy;
	}
}