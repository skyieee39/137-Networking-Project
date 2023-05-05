package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.HarFreButton;

import java.util.ArrayList;
import java.util.List;

public class GameMenu {

	public static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static final double WINDOW_HEIGHT = screenBounds.getHeight();
	public static final double WINDOW_WIDTH = screenBounds.getWidth();
	private static final double BUTTON_OFFSET = 20.0;
	private static final double MENU_STARTX = WINDOW_WIDTH/8.0;
	private final Image logo = new Image("/model/resources/logo_wo_bg.png", 500, 500, false,false);
	private AnchorPane pane;
	private Scene scene;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private VBox menuBox;
	List<HarFreButton> menuBtns;

	//the class constructor
	public GameMenu() {
		menuBtns = new ArrayList<>();
		pane = new AnchorPane();
		scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
		canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();

	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.gc.drawImage(this.logo, (WINDOW_WIDTH - MENU_STARTX) - 500/2, 0);
		//set stage elements here
		pane.getChildren().add(this.canvas);
		createMenuButtons();
		this.stage = stage;
		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);

		// For Full Screen
		this.stage.initStyle(StageStyle.UNDECORATED);
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

	// Menu Buttons creation
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
		if (btn.equals(this.menuBtns.get(0))){
			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					GameStage gamestage = new GameStage();
					gamestage.setStage(stage);
				}
			});
		} else if (btn.equals(this.menuBtns.get(4))){
			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.exit(0);
				}
			});
		}
	}
}
