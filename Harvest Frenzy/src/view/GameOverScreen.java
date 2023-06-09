package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.HarFreButton;

public class GameOverScreen {
	private static final String TEXT1 = "Game Over!"; 
	private static final String TEXT2 = "Your Score Is:";
	private final String FONT_STYLE = "src/model/resources/fonts/Silkscreen/slkscrb.ttf";
	private static final String BGSRC = "'/view/resources/night_sky.png'";
	private String score;
	public static boolean fullscreen = true;
	private AnchorPane pane;
	private Scene scene;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnchorPane bgpane;
	private BoxBlur blur;
	private AnchorPane instBox;
	private VBox vertbox;
	
	public GameOverScreen(int score) {
		this.score = Integer.toString(score);
		pane = new AnchorPane();
		bgpane = new AnchorPane();
		scene = new Scene(pane, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		canvas = new Canvas(GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		blur = new BoxBlur();
		vertbox = new VBox();
	}
	// === STAGE SETUP ===
		public void setStage(Stage stage) {
			this.stage = stage;
			this.stage.setTitle("Harvest Frenzy");
			this.stage.setScene(scene);

			// For Full Screen
			setFullScreen();
			initBlur();
			createBackground(bgpane, BGSRC);
			pane.getChildren().addAll(bgpane, canvas);
			createIBox();
			this.stage.show();
		}

		// Creation
		private void createIBox() {
			instBox = new AnchorPane();
			instBox.setPrefHeight(500);
			instBox.setPrefWidth(500);

			HarFreButton backbtn = new HarFreButton("EXIT");
//			initBackButton(backbtn);

			initIText();

			instBox.getChildren().add(vertbox);
			pane.getChildren().add(instBox);
		}

		private void initIText() {
			Text text1 = new Text();
			text1.setText(TEXT1);
			setFont(text1);
			text1.setFill(Paint.valueOf("white"));
			Text text2 = new Text();
			text2.setText(TEXT2);
			setFont(text2);
			text2.setFill(Paint.valueOf("white"));
			Text scoret = new Text();
			scoret.setText(score);
			setFont(scoret);
			scoret.setFill(Paint.valueOf("white"));

			vertbox.getChildren().addAll(text1,text2,scoret);
			vertbox.setLayoutX(GameMenu.WINDOW_WIDTH/2 - 250);

			vertbox.setLayoutY(GameMenu.WINDOW_HEIGHT/2 - 250);
		}

//		private void initBackButton(HarFreButton btn) {
//			btn.setLayoutX((BOX_WIDTH - BOX_WIDTH/5) - btn.getHFBWidth());
//			btn.setLayoutY(BOX_HEIGHT - BOX_HEIGHT/4);
//			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				public void handle(MouseEvent arg0) {
//					gm.setStage(stage);
//				}
//			});
//		}

		// === SET FONT ===
		private void setFont(Text text){
			try {
				text.setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 30));
			} catch (FileNotFoundException e){
				text.setFont(Font.font("Arial", 30));
			}
		}

		// === SET BLUR ===
		private void initBlur() {
			blur.setHeight(7);
			blur.setWidth(7);
			blur.setIterations(1);
			bgpane.setPrefHeight(GameMenu.WINDOW_HEIGHT);
			bgpane.setPrefWidth(GameMenu.WINDOW_WIDTH);
			bgpane.setEffect(blur);
		}

		// === SET BACKGROUND ===
		private void createBackground(Node node, String src) {
			node.setStyle(
					"-fx-background-image: url(" +
		                src +
		            "); " +
		            "-fx-background-size: cover;"
		       );
		}

		// === SET FULL SCREEN ===
		private void setFullScreen() {
			if (fullscreen) {
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
