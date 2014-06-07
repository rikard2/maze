package com.boss.maze;

import org.andengine.util.color.Color;

public class Cell {
	public boolean openTop = true;
	public boolean openRight = true;
	public boolean openLeft = true;
	public boolean openBottom = true;
	public Color color = new Color(0.9f, 0.9f, 0.9f);
}
