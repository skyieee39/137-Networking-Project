package view;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

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
import javafx.stage.Stage;
import model.HarFreButton;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionsPage {
	public static final double OFFSET = 20.0;
	private final String FONT_STYLE = "src/model/resources/fonts/Silkscreen/slkscrb.ttf";
	private static final String BGSRC = "'/view/resources/night_sky.png'";
	private static final String BOXSRC = "'/model/resources/instructions_box.png'";
	private static final String ITEXT1 = "-> Use the left and right keys to move your player";
	private static final String ITEXT2 = "-> Use the space bar to jump! Twice, to double-jump";
	private static final String ITEXT3 = "You have 60 SECONDS to collect as much fruits as you can";
	private static final double BOX_HEIGHT = 1080 * (1080/GameMenu.WINDOW_HEIGHT);
	private static final double BOX_WIDTH = 1920 * (1920/GameMenu.WINDOW_WIDTH);
	private List<String> instTexts = Arrays.asList(ITEXT1, ITEXT2, ITEXT3);
	public static boolean fullscreen = true;
	private AnchorPane pane;
	private Scene scene;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnchorPane bgpane;
	private BoxBlur blur;
	private AnchorPane instBox;
	private GameMenu gm;

	public InstructionsPage(GameMenu gmen) {
		pane = new AnchorPane();
		bgpane = new AnchorPane();
		scene = new Scene(pane, GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		canvas = new Canvas(GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		blur = new BoxBlur();
		gm = gmen;
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
		instBox.setPrefHeight(BOX_HEIGHT);
		instBox.setPrefWidth(BOX_WIDTH);
		createBackground(instBox, BOXSRC);

		HarFreButton backbtn = new HarFreButton("BACK");
		initBackButton(backbtn);

		initIText();

		instBox.getChildren().add(backbtn);
		pane.getChildren().add(instBox);
	}

	private void initIText() {
		for (int i = 0; i < instTexts.size(); i++) {
			Text text = new Text();
			text.setText(instTexts.get(i));
			setFont(text);
			text.setFill(Paint.valueOf("white"));
			if (i == 0) {
				text.setLayoutX(BOX_WIDTH/2.76 + ITEXT1.length());
				text.setLayoutY(BOX_HEIGHT * .3);
			} else if (i == 1) {
				text.setLayoutX(BOX_WIDTH/2.76 + ITEXT2.length());
				text.setLayoutY(BOX_HEIGHT * .41);
			} else {
				text.setLayoutX((BOX_WIDTH/2) - (ITEXT3.length()*7));
				text.setLayoutY(BOX_HEIGHT * .6);
			}

			instBox.getChildren().add(text);

		}
	}

	private void initBackButton(HarFreButton btn) {
		btn.setLayoutX((BOX_WIDTH - BOX_WIDTH/5) - btn.getHFBWidth());
		btn.setLayoutY(BOX_HEIGHT - BOX_HEIGHT/4);
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				gm.setStage(stage);
			}
		});
	}

	// === SET FONT ===
	private void setFont(Text text){
		try {
			text.setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 20));
		} catch (FileNotFoundException e){
			text.setFont(Font.font("Arial", 20));
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