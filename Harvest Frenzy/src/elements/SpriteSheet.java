package elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.image.Image;

public class SpriteSheet{
	private final static int CYCLE_DURATION = 4; // 1 cycle is 5 frames
	private final String sheetname;
	private final int cycle;
	private final int width;
	private final int height;
	private Image playerImg;
	private int curRow = 2;
	private int row;
	private int cycleCount = 0;
	private int frame = 0;
	private Player player;
	List<Image> sheet;
	// sheetname ordered by index
	private static final List<String> names = new ArrayList<String>() {{
		add("MANG_DOMAK");
	}};

	public SpriteSheet(int sheetnum, int cycle, int row, int width, int height, Player player) {
		this.sheetname = names.get(sheetnum);
		this.cycle = cycle;
		this.row = row;
		this.width = width;
		this.height = height;
		this.player = player;
		sheet = new ArrayList<>();
		setUpSheet();
		playerImg = sheet.get(2);
	}
	private void setUpSheet() {
		System.out.println("SETTING UP...");
		for(int i = 0; i < row; i++) {
			if (i == 3) {
				System.out.println("Adding: /model/resources/sprites/player/"+sheetname+"/"+i+0+".png");
				Image temp = new Image("/model/resources/sprites/player/"+sheetname+"/"+i+0+".png", width, height, false, false);
				sheet.add(temp);
			} else {
				for (int j = 0; j < cycle; j++) {
					System.out.println("Adding: /model/resources/sprites/player/"+sheetname+"/"+i+j+".png");
					Image temp = new Image("/model/resources/sprites/player/"+sheetname+"/"+i+j+".png", width, height, false, false);
					sheet.add(temp);
					System.out.println("ADDED!");
				}
			}
		}
	}

	// This method is called every time the handle is called
	public void play() {
			cycleCount++;
			if (cycleCount < cycle) {
				if (frame < CYCLE_DURATION) {
					frame++;
				} else {
					if(curRow == 3) {
						cycleCount = 0;
					}
					playerImg = sheet.get(curRow*cycle+cycleCount);
					frame = 0;
					System.out.println("DISPLAYING CYCLE: "+curRow+cycleCount);
				}
			} else {
				cycleCount = 0;
			}

	}

	public void setState(int row) {
		curRow = row;
	}

	public Image getImg() {
		return playerImg;
	}
}
