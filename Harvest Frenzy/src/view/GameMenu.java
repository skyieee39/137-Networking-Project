package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HarFreButton;
import app.ChatController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import app.CloudTimer;

public class GameMenu {

	public static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static final double WINDOW_HEIGHT = screenBounds.getHeight();
	public static final double WINDOW_WIDTH = screenBounds.getWidth();
	public static final double BUTTON_OFFSET = 20.0;
	public static final double MENU_STARTX = WINDOW_WIDTH/8.0;
	public static boolean fullscreen = true;
	private AnchorPane pane;
	private Scene scene;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private VBox menuBox;
	private CloudTimer cloudtimer;
	List<HarFreButton> menuBtns;

	// === CONSTRUCTOR ===
	public GameMenu() {
		menuBtns = new ArrayList<>();
		pane = new AnchorPane();
		scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
		canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		cloudtimer = new CloudTimer(gc);
	}

	// === STAGE SETUP ===
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(scene);

		// For Full Screen
		this.stage.initStyle(StageStyle.UNDECORATED);
		setFullScreen();

		cloudtimer.start();
		pane.getChildren().add(this.canvas);
		createMenuButtons();
		createBackground();
		this.stage.show();
	}

	// === BUTTONS ===
	// Creation
	private void createMenuButtons() {
		initStartButton();
		initInstructionButton();
		initAboutButton();
		initHelpButton();
		initExitButton();

		// Add event handlers
		for (int i = 0; i < menuBtns.size(); i++) {
			addEventHandler(menuBtns.get(i));
		}

		final double menuBoxHeight = ((menuBtns.get(0).getHFBHeight() + BUTTON_OFFSET) * menuBtns.size())/2;
		final double menuBoxWidth = menuBtns.get(0).getHFBWidth()/2;

		// Menu Buttons Container
		menuBox = new VBox();
		menuBox.setSpacing(BUTTON_OFFSET);
		menuBox.getChildren().addAll(menuBtns);
		menuBox.setLayoutX(MENU_STARTX - menuBoxWidth);
		menuBox.setLayoutY(WINDOW_HEIGHT/2 - menuBoxHeight);
		pane.getChildren().add(menuBox);
	}
	private void initMenuButtons(HarFreButton btn) {
		menuBtns.add(btn);
	}
	private void initStartButton() {
		HarFreButton startbtn = new HarFreButton("START GAME");
		initMenuButtons(startbtn);
	}
	private void initInstructionButton() {
		HarFreButton instructbtn = new HarFreButton("INSTRUCTIONS");
		initMenuButtons(instructbtn);
	}
	private void initAboutButton() {
		HarFreButton aboutbtn = new HarFreButton("ABOUT");
		initMenuButtons(aboutbtn);
	}
	private void initHelpButton() {
		HarFreButton helpbtn = new HarFreButton("HELP");
		initMenuButtons(helpbtn);
	}
	private void initExitButton() {
		HarFreButton exitbtn = new HarFreButton("EXIT");
		initMenuButtons(exitbtn);
	}

	//Button Event Handler
	private void addEventHandler(Button btn) {
		if (btn.equals(menuBtns.get(0))){
			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					ChatController chatcontroller = new ChatController();
					chatcontroller.setStage(stage);
				}
			});
		} else if (btn.equals(menuBtns.get(4))){
			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.exit(0);
				}
			});
		}
	}

	// === SET BACKGROUND ===
	private void createBackground() {
		pane.setStyle(
				"-fx-background-image: url(" +
	                "'/view/resources/night_sky.png'" +
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
