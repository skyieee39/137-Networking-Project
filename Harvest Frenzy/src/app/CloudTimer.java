package app;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Cloud;
import view.GameMenu;

public class CloudTimer extends AnimationTimer {
	private GraphicsContext gc;
	private Cloud clouda, cloudb, cloudc;
	private final Image cloudaImg = new Image("/model/resources/night_sky_cloud_a.png", GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT, false,false);
	private final Image cloudbImg = new Image("/model/resources/night_sky_cloud_b.png", GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT, false,false);
	private final Image cloudcImg = new Image("/model/resources/night_sky_c.png", GameMenu.WINDOW_WIDTH, GameMenu.WINDOW_HEIGHT, false,false);
	private final Image logo = new Image("/view/resources/logo_wo_bg.png", 500, 500, false,false);


	public CloudTimer(GraphicsContext gc){
		this.gc = gc;
		this.clouda = new Cloud(cloudaImg, .15, 140, 85);
		this.cloudb = new Cloud(cloudbImg, .085, 100, -30);
		this.cloudc = new Cloud(cloudcImg, .05, -30, -120);
	}

	@Override
	public void handle(long currentNano) {
		this.gc.clearRect(0, 0, GameMenu.WINDOW_WIDTH,GameMenu.WINDOW_HEIGHT);
		this.clouda.move();
		this.cloudb.move();
		this.cloudc.move();

		this.clouda.render(this.gc);
		this.cloudb.render(this.gc);
		this.cloudc.render(this.gc);
		this.gc.drawImage(this.logo, (GameMenu.WINDOW_WIDTH - GameMenu.MENU_STARTX) - 500/2, 0);
	}
}
