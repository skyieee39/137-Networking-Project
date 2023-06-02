package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import view.GameMenu;

public class ChatUI {

	private final String FONT_STYLE = "src/model/resources/fonts/Silkscreen/slkscrb.ttf";
	private final double CHAT_LENGTH = GameMenu.WINDOW_HEIGHT/5;
	private final double CHAT_WIDTH = GameMenu.WINDOW_WIDTH/4;
	public ArrayList<String> chats;
	public GraphicsContext gc;
	private VBox chatBox;
	private AnchorPane pane;

	public ChatUI() {
		chats = new ArrayList<>();
		chatBox = new VBox();
		pane = new AnchorPane();
		initChats();
		createVBox();
	}

	private void initChats() {
		chats.add("Enter your name: ");
		chats.add("Echo: hello");
		chats.add("You: hi!");

	}
	// Sets the font style of the chat
	private void setFont(Text t){
		try {
			t.setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 20));
		} catch (FileNotFoundException e){
			t.setFont(Font.font("Arial", 20));
		}
	}

	private void createVBox() {
		for(int i = 0; i < chats.size(); i++) {
			Text text = new Text();
			text.setText(chats.get(i));
			setFont(text);
			chatBox.getChildren().add(text);
		}
		chatBox.setLayoutX(CHAT_LENGTH);
		chatBox.setLayoutY(CHAT_WIDTH);
		pane.getChildren().add(chatBox);
	}

    public AnchorPane getPane() {
    	return pane;
    }
}
