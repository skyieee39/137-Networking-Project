package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class GameStage {

	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;

	// List of keys to lock
	public boolean keyJumpLock = false;

	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.gc, this.scene, this);
	}

	// method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;

		this.gametimer.start();

		// set stage elements here
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);

		// for full screen
		setFullScreen();
		keyPressEvent();
		this.stage.show();
	}

	private void setFullScreen() {
		if (GameMenu.fullscreen) {
			stage.setFullScreen(true);
			stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {

	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable,
	                    Boolean oldValue, Boolean newValue) {
	                if(newValue != null && !newValue.booleanValue())
	                    stage.setFullScreen(true);
	            }
	        });
		} else {
			stage.setFullScreen(false);
		}
	}

	private void keyPressEvent() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				gametimer.movePlayer(e.getCode());
			}
		});

		this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				gametimer.haltPlayer(e.getCode());
			}
		});
	}
}