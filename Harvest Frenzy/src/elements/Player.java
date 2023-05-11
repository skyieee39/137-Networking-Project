package elements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import view.GameMenu;

public class Player extends Sprite {
	// --- PLAYER CONSTANTS ---
	private final static int PLAYER_HEIGHT = 128;
	private final static int PLAYER_WIDTH = 96;
	private final static double MOVE_SPEED = 8;
	private final static double JUMP_HEIGHT = 28;
	private final static Image PLAYER_IMAGE = new Image ("/model/resources/player_placeholder.png", PLAYER_WIDTH, PLAYER_HEIGHT, false, false);
	// --- PLAYER ATTRIBUTES ---
	private String name;
	private int score;
	private double veloX = 0;
	private double veloY = 0;
	// --- ENVIRONMENT CONSTANTS ---
	private final static int MAX_JUMP_COUNT = 2;
	private final static double GRAVITY_CONSTANT = 10;
	// --- ENVIRONMENT ATTRIBUTES ---
	private boolean playerGravity = true;
	private boolean isJumping = false;
	private boolean keyJumpLock = false;
	private int jumpLockCounter = 0;
	private int jumpCount = 0;
	private List<String> activeKeys;

	// === Constructor ===
	public Player(double xPos, double yPos) {
		super(xPos, yPos);
		activeKeys = new ArrayList<>();
		loadImage(PLAYER_IMAGE);
		name = "Player";
		score = 0;
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
				addActiveDirection("R");
				smoothDirection();
				break;
			case LEFT:
				addActiveDirection("L");
				smoothDirection();
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
				remActiveDirection("R");
				smoothDirection();
				break;
			case LEFT:
				remActiveDirection("L");
				smoothDirection();
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
		if((getY() + PLAYER_HEIGHT) < GameMenu.WINDOW_HEIGHT) {	// Player is not touching the ground
			if(playerGravity) {
				if (jumpCount > 1) {
					setVeloY(GRAVITY_CONSTANT*1.8);
				} else {
					setVeloY(GRAVITY_CONSTANT);
				}
			}
		} else {
			setJumpLock(false);
			isJumping = false;
			setVeloY(0);
			makeGrounded();
			// Will only reset jump count to 0 once the player touches the ground
			jumpCount = 0;
		}

		// Teleport the player to the other side once it reaches the end of the window
		if((getX() + PLAYER_WIDTH) > GameMenu.WINDOW_WIDTH) {
			setX(-GameMenu.WINDOW_WIDTH);
		}
		if((getX()) < 0) {
			setX(GameMenu.WINDOW_WIDTH);
		}
	}

	private void makeGrounded() {
		y = GameMenu.WINDOW_HEIGHT - PLAYER_HEIGHT;
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
					if (jumpCount == 0) {
						setVeloY(-JUMP_HEIGHT);		// Sets jump distance to 25 units up per frame until 8 frames
					} else {
						setVeloY(-(JUMP_HEIGHT + JUMP_HEIGHT*0.4));
					}
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

	// === DIRECTION LOCK FUNCTIONS ===
	private void addActiveDirection(String Key) {
		if (!activeKeys.contains(Key)) {
			activeKeys.add(Key);
		}

	}
	private void remActiveDirection(String Key) {
		if (activeKeys.contains(Key)) {
			activeKeys.remove(Key);
		}

	}
	private void smoothDirection() {
		if (activeKeys.size() != 0) {
			if (activeKeys.get(0) == "R") {
				setVeloX(MOVE_SPEED);
			} else if (activeKeys.get(0) == "L") {
				setVeloX(-MOVE_SPEED);
			}
		} else {
			setVeloX(0);
		}

	}

	// === Getters ===
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}

	// === Setters ===
	public void setVeloX(double vx) {
		veloX = vx;
	}
	public void setVeloY(double vy) {
		veloY = vy;
	}
	public void setScore(int points) {
		score += points;
	}
}