package com.boss.maze;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import android.provider.CalendarContract.Colors;

public class Chamber {
	public int CELL_WIDTH = 45;
	public int CHAMBER_WIDTH = 10;
	public int CHAMBER_HEIGHT = 10;
	public int LEFT = 10;
	public int TOP = 10;
	
	private MainActivity mActivity;
	
	private Cell[][] mCells;
	
	public Chamber (MainActivity activity) {
		mActivity = activity;
		mCells = createCells();
		
		//recursiveSplit(mCells);
	}
	
	private void recursiveSplit(Cell[][] mCells) {
		recursiveSplit(mCells, 0, 0, CHAMBER_WIDTH - 1, CHAMBER_HEIGHT - 1, false);
	}
	private void recursiveSplit(Cell[][] mCells, int x1, int y1, int x2, int y2, boolean last) {
		// halvera två gånger
		int x_split = -1;
		int y_split = -1;
		
		int xLength = x2 - x1;
		int yLength = y2 - y1;
		
		if (xLength < 1) {
			return; // avbryt rekursionen
		}
		
		if (yLength <= 1) {
			return; // avbryt rekursionen
		}
		//x_split = randBetween(x1, x2 - 1);
		//y_split = randBetween(y1, y2 - 1);
		x_split = x2 - 1;
		y_split = y2 - 1;
		
		// Gå igenom x-kolumnen
		for (int y = y1; y <= y2; y++) {
			mCells[x_split][y].openRight = false;
			mCells[x_split][y].color = Color.BLUE;
		}
		
		// Gå igenom y-raden
		for (int x = x1; x <= x2; x++) {
			mCells[x][y_split].openBottom = false;
			mCells[x][y_split].color = Color.YELLOW;
		}
		
		if (last)
			return;
		
		recursiveSplit(mCells, 0, 0, x_split, y_split, false);
	}
	
	private int randBetween (int min, int max) {
		Random r = new Random();
		max += 1;
		
		return r.nextInt(max - min) + min;
	}

	public Cell[][] createCells() {
		Cell[][] ret = new Cell[CHAMBER_WIDTH][CHAMBER_HEIGHT];
		
		for (int x = 0; x < CHAMBER_WIDTH; x++) {
			for (int y = 0; y < CHAMBER_HEIGHT; y++) {
				ret[x][y] = new Cell();
				
				// öppna kanterna runt om
				ret[x][y].openLeft = !(x == 0);
				ret[x][y].openRight = !(x == (CHAMBER_WIDTH - 1));
				ret[x][y].openTop = !(y == 0);
				ret[x][y].openBottom = !(y == (CHAMBER_HEIGHT - 1));
			}
		}
		
		return ret;
	}
	
	public void Draw(Scene scene, VertexBufferObjectManager vbom) {
		for (int x = 0; x < mCells.length; x++) {
			for (int y = 0; y < mCells[x].length; y++) {
				Cell cell = mCells[x][y];
				
				Line topLine = createLine(
						LEFT + x * CELL_WIDTH,
						TOP + y * CELL_WIDTH,
						LEFT + (x + 1) * CELL_WIDTH,
						TOP + y  * CELL_WIDTH,
						vbom,
						Color.RED
					);
				
				Line leftLine = createLine(
						LEFT + x * CELL_WIDTH,
						TOP + y * CELL_WIDTH,
						LEFT + x * CELL_WIDTH,
						TOP + (y + 1)  * CELL_WIDTH,
						vbom,
						Color.RED
					);
				
				Line rightLine = createLine(
						LEFT + (x + 1) * CELL_WIDTH,
						TOP + y * CELL_WIDTH,
						LEFT + (x + 1) * CELL_WIDTH,
						TOP + (y + 1)  * CELL_WIDTH,
						vbom,
						Color.RED
					);
				
				Line bottomLine = createLine(
						LEFT + x * CELL_WIDTH,
						TOP + (y + 1) * CELL_WIDTH,
						LEFT + (x + 1) * CELL_WIDTH,
						TOP + (y + 1)  * CELL_WIDTH,
						vbom,
						Color.RED
					);
				
				int padding = 1;
				Rectangle rect = new Rectangle(
						LEFT + x * CELL_WIDTH + padding,
						TOP + y * CELL_WIDTH + padding,
						CELL_WIDTH - padding,
						CELL_WIDTH - padding,
						vbom);
				final Text centerText = new Text(
						(int)(LEFT + (x) * CELL_WIDTH) + 2,
						(int)(TOP + (y) * CELL_WIDTH) + 2,
						mActivity.mFont,
						Integer.toString(x) + "," + Integer.toString(y),
						new TextOptions(HorizontalAlign.CENTER), 
						vbom);
				centerText.setColor(new Color(0.6f, 0.6f, 0.6f));
								
				rect.setColor(cell.color);
				scene.attachChild(rect);
				
				scene.attachChild(centerText);

				
				if (!cell.openTop)
					scene.attachChild(topLine);
				
				if (!cell.openBottom)
					scene.attachChild(bottomLine);
				
				if (!cell.openLeft)
					scene.attachChild(leftLine);
				
				if (!cell.openRight)
					scene.attachChild(rightLine);
			}
		}
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
				3, 		// line width
				vbom	// vertex buffer object manager
			);
		
		line.setColor(color);
		
		return line;
		
	}
}
