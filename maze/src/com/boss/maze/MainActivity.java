package com.boss.maze;

import java.util.Random;

import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

public class MainActivity extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 720;
	private static final int ROWS = 15;
	private static final int COLUMNS = 10;
	private static final float ROW_HEIGHT = CAMERA_HEIGHT / ROWS;
	private static final float COL_WIDTH = CAMERA_WIDTH / COLUMNS;
	
	/* COLORS */
	private static final Color AIR_COLOR = Color.RED;
	private static final Color BRICK_COLOR = Color.BLACK;
	
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		final org.andengine.engine.camera.Camera camera = new org.andengine.engine.camera.Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);

	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Scene onCreateScene() {
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));
		

		int nextCol = randBetween(0, COLUMNS);
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				
				 Rectangle rect = createBoxRectangle(y, x);
				 if (x == nextCol) {
					 rect.setColor(AIR_COLOR);
				 } else {
					 rect.setColor(BRICK_COLOR);
				 }
				 
				 scene.attachChild(rect);
				 randBetween(0, ROWS);
			}
			
			nextCol = nextAir(nextCol);
		}
		
		return scene;
	}
	
	private int nextAir(int nextCol) {
		int n = nextCol;
		
		if (n == 0)
			return 1;
		
		if (n == COLUMNS) {
			return COLUMNS - 1;
		}
		
		while (n == nextCol) {
			n = randBetween(nextCol - 1, nextCol + 1);
		}
		
		return n;
	}

	private Rectangle createBoxRectangle (int row, int column) {
		return new Rectangle(
			column * COL_WIDTH,
			row * ROW_HEIGHT,
			COL_WIDTH,
			ROW_HEIGHT,
			this.getVertexBufferObjectManager()
		);
	}
	
	private int randBetween (int min, int max) {
		Random r = new Random();
		max += 1;
		
		return r.nextInt(max - min) + min;
	}

}
