package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameMenu;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		GameMenu MenuStage = new GameMenu();
		MenuStage.setStage(stage);
	}
}
