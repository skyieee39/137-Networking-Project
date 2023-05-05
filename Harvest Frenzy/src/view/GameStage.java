package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import elements.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class GameStage {

	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private Player player1;

	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.player1 = new Player(500, 500);

	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;

		//set stage elements here
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);
		// For Full Screen
		setFullScreen();

		this.renderImages();

		this.stage.show();
	}

	public void renderImages() {
		this.player1.render(this.gc);
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