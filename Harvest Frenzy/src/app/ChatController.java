package app;

import view.LobbySystem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChatController {
	private LobbySystem controller;
	private FXMLLoader loader;
	private Stage stage;
	
	public ChatController() {
		this.loader = new FXMLLoader(Main.class.getResource("/view/ViewMultiplayer.fxml"));
		this.controller = (LobbySystem) loader.getController();
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		try {
			AnchorPane homeUtente = (AnchorPane) loader.load();
			Scene scene = new Scene(homeUtente);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.stage.setTitle("Harvest Frenzy: Chat Lobby");
			this.stage.setScene(scene);
			this.stage.setResizable(false);
			controller.setStage(stage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}