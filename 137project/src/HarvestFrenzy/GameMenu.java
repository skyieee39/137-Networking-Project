package HarvestFrenzy;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import HarvestFrenzy.GameStage;

public class GameMenu {

	public static final int WINDOW_HEIGHT = 970;
	public static final int WINDOW_WIDTH = 1000;
	private StackPane pane;
	private Scene scene;
	private Stage stage;
	private Canvas canvas;
	private GraphicsContext gc;
	private final Image titlescreen = new Image("images/menuscreen.png", WINDOW_WIDTH, WINDOW_HEIGHT,false,false);
	private Button startbtn = new Button("Start Game");
	private Button instructbtn = new Button("Instructions");
	private Button aboutbtn = new Button("About");


	//the class constructor
	public GameMenu() {
		this.pane = new StackPane();
		this.scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();

	}


	//method to add the stage elements
	public void setStage(Stage stage) {
		this.gc.drawImage(this.titlescreen, 0, 0);



		//position buttons
		this.startbtn.setTranslateY(430);
		this.startbtn.setTranslateX(0);
		this.startbtn.setPrefSize(120, 40);
		this.instructbtn.setTranslateY(215);
		this.instructbtn.setTranslateX(-338);
		this.aboutbtn.setTranslateY(215);
		this.aboutbtn.setTranslateX(350);

		//button designs
		this.startbtn.setStyle("-fx-text-fill:white; -fx-background-color:#ADA534");
		this.instructbtn.setStyle("-fx-text-fill:white; -fx-background-color:#DC143C");
		this.aboutbtn.setStyle("-fx-text-fill:white; -fx-background-color:#DC143C");
		Font font = Font.font("Arial", FontPosture.ITALIC, 15);
		this.startbtn.setFont(font);
		this.instructbtn.setFont(font);
		this.aboutbtn.setFont(font);

		this.addEventHandler(startbtn);
		//this.addEventHandler(instructbtn);
		//this.addEventHandler(aboutbtn);

		//set stage elements here
		pane.getChildren().add(this.canvas);
		pane.getChildren().add(startbtn);
		//pane.getChildren().add(aboutbtn);
		//pane.getChildren().add(instructbtn);
		this.stage = stage;


		this.stage.setTitle("Harvest Frenzy");
		this.stage.setScene(this.scene);



		this.stage.show();
	}

	private void addEventHandler(Button btn) {
		if (btn.equals(this.startbtn)){
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				GameStage gamestage = new GameStage();
				gamestage.setStage(stage);
				}
			});
		}
	}
}
