package view;

//import javafx.animation.PauseTransition;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
//import javafx.util.Duration;
import javafx.stage.StageStyle;

public class GameStage {

	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;

	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();

	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;

		//set stage elements here
		this.root.getChildren().add(canvas);
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);
		// For Full Screen
		this.stage.setFullScreen(true);
		this.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		this.stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                if(newValue != null && !newValue.booleanValue())
                    stage.setFullScreen(true);
            }
        });
		this.stage.show();
	}

}