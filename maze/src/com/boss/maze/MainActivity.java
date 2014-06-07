package com.boss.maze;

import java.util.Random;

import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

public class MainActivity extends SimpleBaseGameActivity {

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 720;
	private static final int CELL_WIDTH = 10;
	
	public Font mFont;
     
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
		this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 12);
		this.mFont.load();
	}
	


	@Override
	protected Scene onCreateScene() {
		Scene scene = new Scene();
		scene.setBackground(new Background(Color.WHITE));
		
		Chamber chamber = new Chamber(this);
		
		chamber.Draw(scene, this.getVertexBufferObjectManager());
		return scene;
	}
	
	private int randBetween (int min, int max) {
		Random r = new Random();
		max += 1;
		
		return r.nextInt(max - min) + min;
	}

}
