package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import elements.Player;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import view.GameMenu;

public class ScoreUI {
	private Player player;
	private Text score;
	private final String SCOREB_STYLE = "-fx-background-image: url('/model/resources/SCORE_BOARD.png');";
	private final String FONT_STYLE = "src/model/resources/fonts/Silkscreen/slkscrb.ttf";
	private static final int SCOREB_HEIGHT = 70;
	private static final int SCOREB_WIDTH = 210;
	private final double CHAT_OFF = (GameMenu.WINDOW_WIDTH/8)/20;
	private AnchorPane pane;

	public ScoreUI(Player player) {
		this.player = player;
		pane = new AnchorPane();
		score = new Text();
		setFont(score);
		initUI();
	}
	private void initUI() {
		pane.setPrefSize(SCOREB_WIDTH, SCOREB_HEIGHT);
		pane.setStyle(SCOREB_STYLE);
		pane.setLayoutX((GameMenu.WINDOW_WIDTH - SCOREB_WIDTH)-CHAT_OFF);
		pane.setLayoutY(CHAT_OFF);
		setScoreText();
		score.setLayoutX(SCOREB_WIDTH/2);
		score.setLayoutY(SCOREB_HEIGHT/2 + 10);
		score.setFill(Paint.valueOf("white"));
	}
	// Sets the font style of the button
	private void setFont(Text text){
		try {
			text.setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 35));
		} catch (FileNotFoundException e){
			text.setFont(Font.font("Arial", 30));
		}
	}

	public void setScoreText() {
		pane.getChildren().clear();
		score.setText(Integer.toString(player.getScore()));
		pane.getChildren().add(score);
	}

	public AnchorPane getPane() {
		return pane;
	}
}
