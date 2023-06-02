package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	private TextField inputField;
	private AnchorPane pane;
	private boolean isTyping;

	public ChatUI() {
		isTyping = false;
		chats = new ArrayList<>();
		chatBox = new VBox();
		inputField = new TextField();
		pane = new AnchorPane();
		initChats();
		createVBox();
	}

	private void initChats() {
		inputField.setEditable(false);
		inputField.setDisable(true);
		inputField.setMaxSize(CHAT_WIDTH, 30);
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
		if (pane.getChildren().contains(chatBox)) {
			chatBox.getChildren().clear();
			pane.getChildren().clear();
		}
		for(int i = 0; i < chats.size(); i++) {
			Text text = new Text();
			text.setText(chats.get(i));
			setFont(text);
			chatBox.getChildren().add(text);
		}
		chatBox.getChildren().add(inputField);
		pane.getChildren().add(chatBox);

	}

	public boolean getIsTyping() {
		return isTyping;
	}
	public void setIsTyping(boolean set) {
		isTyping = set;
		if (set == true) {
			inputField.setEditable(true);
			inputField.setDisable(false);
		} else {
			inputField.setEditable(false);
			inputField.setDisable(true);
			if(!checkChatSize()) {
				chats.remove(0);
			}
			chats.add(inputField.getText());
			inputField.setText("");
			createVBox();
		}
	}
	public boolean checkChatSize() {
		if (chats.size() < 5) {
			return true;
		}
		return false;
	}
    public AnchorPane getPane() {
    	return pane;
    }
}
