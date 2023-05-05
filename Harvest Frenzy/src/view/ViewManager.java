package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.HarFreButton;

public class ViewManager {


	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	public static final int WINDOW_HEIGHT = 970;
	public static final int WINDOW_WIDTH = 1000;
	private static final int BUTTON_OFFSET = 100;
	List<HarFreButton> menuBtns;

	public ViewManager(){
		menuBtns = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createMenuButtons();
	}

	public Stage getMainStage() {
		return mainStage;
	}


	// Menu Buttons creation
	private void createMenuButtons() {
		createStartButton();
		createInstructionButton();
		createAboutButton();
		createHelpButton();
		createExitButton();
	}
	private void initMenuButtons(HarFreButton btn) {
		btn.setLayoutX((WINDOW_WIDTH/6.0) - (btn.getHFBWidth()/2.0));
		btn.setLayoutY(WINDOW_HEIGHT/5.0 + menuBtns.size() * BUTTON_OFFSET);
		menuBtns.add(btn);
		mainPane.getChildren().add(btn);
	}
	private void createStartButton() {
		HarFreButton startbtn = new HarFreButton("START GAME");
		initMenuButtons(startbtn);
	}
	private void createInstructionButton() {
		HarFreButton instructbtn = new HarFreButton("INSTRUCTIONS");
		initMenuButtons(instructbtn);
	}
	private void createAboutButton() {
		HarFreButton aboutbtn = new HarFreButton("ABOUT");
		initMenuButtons(aboutbtn);
	}
	private void createHelpButton() {
		HarFreButton helpbtn = new HarFreButton("HELP");
		initMenuButtons(helpbtn);
	}
	private void createExitButton() {
		HarFreButton exitbtn = new HarFreButton("EXIT");
		initMenuButtons(exitbtn);
	}
}
