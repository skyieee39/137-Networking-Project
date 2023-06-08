package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import model.ChatUI;
import model.ScoreUI;

public class GameStage {

	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	public ChatUI chat;
	public ScoreUI score;

	//the class constructor
	public GameStage() {
		root = new Group();
		scene = new Scene(root, GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		canvas = new Canvas(GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		gametimer = new GameTimer(this.gc, this.scene, this);
		chat = new ChatUI();
		score = new ScoreUI(gametimer.getPlayer());
	}

	// method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		// set stage elements here
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);

		// for full screen
		setFullScreen();
		this.root.getChildren().addAll(canvas, chat.getPane(), score.getPane());
		this.gametimer.start();
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
}