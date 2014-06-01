package com.boss.maze;

import java.util.Random;

import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

public class MainActivity extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 720;
	private static final int CELL_WIDTH = 10;
	
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		final org.andengine.engine.camera.Camera camera = new org.andengine.engine.camera.Camera(
				0,
				0,
				CAMERA_WIDTH,
				CAMERA_HEIGHT
			);

		return new EngineOptions(
				true,
				ScreenOrientation.PORTRAIT_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				camera
			);

	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Scene onCreateScene() {
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));
		
		Chamber chamber = new Chamber();
		
		chamber.Draw(scene, this.getVertexBufferObjectManager());
		return scene;
	}
	
	private int randBetween (int min, int max) {
		Random r = new Random();
		max += 1;
		
		return r.nextInt(max - min) + min;
	}

}
