package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class HarFreButton extends Button{
	private final String FONT_STYLE = "src/model/resources/fonts/Silkscreen/slkscrb.ttf";
	private final String BTN_RELEASED_STYLE = "-fx-text-fill:white; -fx-background-color: transparent; -fx-scale-x: 1; -fx-scale-y: 1; -fx-background-image: url('/model/resources/BUTTON_RELEASE.png');";
	private final String BTN_PRESSED_STYLE = "-fx-text-fill:white; -fx-background-color: transparent; -fx-scale-x: 1; -fx-scale-yx: 1; -fx-background-image: url('/model/resources/BUTTON_PRESSED.png');";
	private final String BTN_HOVER_STYLE = "-fx-text-fill:white; -fx-background-color: transparent; -fx-scale-x: 1.05; -fx-scale-y: 1.05; -fx-background-image: url('/model/resources/BUTTON_HOVER.png');";
	private static final int BUTTON_HEIGHT = 70;
	private static final int BUTTON_WIDTH = 210;

	// Constructor
	public HarFreButton(String text){
		setText(text);
		setFont();
		setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		setStyle(BTN_RELEASED_STYLE);
		initializeListeners();
	}

	// Sets the font style of the button
	private void setFont(){
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_STYLE), 19));
		} catch (FileNotFoundException e){
			setFont(Font.font("Arial", 19));
		}
	}

	// Changes the graphics depending on the state of the button
	private void setOnRelease(){
		setStyle(BTN_RELEASED_STYLE);
	}
	private void setOnClick(){
		setStyle(BTN_PRESSED_STYLE);
	}
	private void setOnHover(){
		setStyle(BTN_HOVER_STYLE);
	}

	// Event listeners
	private void initializeListeners(){
		setOnMousePressed(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.getButton().equals(MouseButton.PRIMARY)){
					setOnClick();
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.getButton().equals(MouseButton.PRIMARY)){
					setOnRelease();
				}
			}
		});

		setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				setOnHover();
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				setOnRelease();
			}
		});

	}

	// Getters
	public int getHFBWidth(){
		return HarFreButton.BUTTON_WIDTH;
	}

	public int getHFBHeight(){
		return HarFreButton.BUTTON_HEIGHT;
	}

}
