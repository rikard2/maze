package com.boss.maze;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class Chamber {
	public int CELL_WIDTH = 30;
	public int CHAMBER_WIDTH = 10;
	public int CHAMBER_HEIGHT = 10;
	public int LEFT = 50;
	public int TOP = 50;
	
	public Chamber () {
		
	}
	
	private void split(Scene scene, VertexBufferObjectManager vbom) {
		split(0, 0, CHAMBER_WIDTH, CHAMBER_HEIGHT, scene, vbom, false);
	}
	private void split(int x1, int y1, int x2, int y2, Scene scene, VertexBufferObjectManager vbom, boolean last) {
		/*
		 * Tänker mig ett rutnät med storleken CHAMBER_WIDTH * CHAMBER_HEIGHT
		 * Splitta varje fyrkant i fyra nya fyrkanter med slumpmässig position i höjd/bredd.
		 */
		
		if ((x2 - x1) < 2) {
			return;
		}
		if ((y2 - y1) < 2) {
			return;
		}
		int x_split = -1;
		while (x_split == x1 || x_split == x2 || x_split == -1)
			x_split = randBetween(x1, x2);
		int y_split = -1;
		while (y_split == y1 || y_split == y2 || y_split == -1)
			y_split = randBetween(y1, y2);
		
		ArrayList<Line> lines = new ArrayList<Line>();
		Color color = last ? Color.RED : Color.GREEN;
		lines.add(createLine(
				LEFT + x_split * CELL_WIDTH,
				TOP + y1 * CELL_WIDTH,
				LEFT + x_split * CELL_WIDTH,
				TOP + y2 * CELL_WIDTH,
				vbom,
				color
			));
		
		lines.add(createLine(
				LEFT + x1 * CELL_WIDTH,
				TOP + y_split * CELL_WIDTH,
				LEFT + x2 * CELL_WIDTH,
				TOP + y_split * CELL_WIDTH,
				vbom,
				color
			));
		
		// splitta alla fyra fyrkanter
		
		for (Line line : lines) {
			scene.attachChild(line);
		}
		
		if (last) {
			return;
		}
		
		split(x1, y1, x_split, y_split, scene, vbom, false);
		split(x1, y_split, x_split, y2, scene, vbom, false);
		split(x_split, y1, x2, y_split, scene, vbom, false);
		split(x_split, y_split, x2, y2, scene, vbom, false);
	}
	
	private int randBetween (int min, int max) {
		Random r = new Random();
		max += 1;
		
		return r.nextInt(max - min) + min;
	}
	
	public void Draw (Scene scene, VertexBufferObjectManager vbom) {
		// skapa fyra linjer (kammaren)
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		// top
		lines.add(createLine(
				LEFT,
				TOP,
				LEFT + CHAMBER_WIDTH * CELL_WIDTH,
				TOP,
				vbom
			));
		
		// bottom
		lines.add(createLine(
				LEFT,
				TOP + CHAMBER_HEIGHT * CELL_WIDTH,
				LEFT + CHAMBER_WIDTH * CELL_WIDTH,
				TOP + CHAMBER_HEIGHT * CELL_WIDTH,
				vbom
			));
		
		// left
		lines.add(createLine(
				LEFT,
				TOP,
				LEFT,
				TOP + CHAMBER_HEIGHT * CELL_WIDTH,
				vbom
			));
		
		// right
		lines.add(createLine(
				LEFT + CHAMBER_WIDTH * CELL_WIDTH,
				TOP,
				LEFT + CHAMBER_WIDTH * CELL_WIDTH,
				TOP + CHAMBER_HEIGHT * CELL_WIDTH,
				vbom
			));
		
		for (Line line : lines) {
			scene.attachChild(line);
		}
		
		split(scene, vbom);
	}
	
	public Line createLine(int x1, int y1, int x2, int y2, VertexBufferObjectManager vbom) {
		return createLine(x1, y1, x2, y2, vbom, Color.BLUE);
	}
	public Line createLine(int x1, int y1, int x2, int y2, VertexBufferObjectManager vbom, Color color) {
		final Line line = new Line(
				x1, 	// x1
				y1, 	// y1
				x2, 	// x2
				y2, 	// y2
				1, 		// line width
				vbom	// vertex buffer object manager
			);
		line.setColor(color);
		
		return line;
	}
}
