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

//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			ViewManager manager = new ViewManager();
//			primaryStage = manager.getMainStage();
//			primaryStage.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
}
