package com.boss.maze;

import org.andengine.util.color.Color;

import android.util.Log;

public class Cell {
	public boolean openTop = true;
	public boolean openRight = true;
	public boolean openLeft = true;
	public boolean openBottom = true;
	public Color color = null;
	public int X;
	public int Y;
	
	public final Color green = Color.GREEN;
	public final Color yellow = Color.YELLOW;
	public final Color red = Color.RED;
	
	
	public Cell(int x, int y) {
		color = green;
		X = x;
		Y = y;
	}
	
	public boolean onEnter() {
		boolean retValue = true;
		if( yellow == color || red == color ) {
			// game over
			retValue = false;
		} else if ( green == color ) {
			color = yellow;
		} else {
			throw new IllegalStateException("Unknown color " + color + " on cell " + this);
		}
		return retValue;
	}
	
	public boolean onExit() {
		boolean retValue = true;
		if( red == color || green == color ) {
			// game over.
			retValue = false;
		} else if ( yellow == color ) {
			color = red;
		} else {
			throw new IllegalStateException("Unknown color " + color + " on cell " + this);
		}
		return retValue;
	}
	
	public boolean moveTo(Cell to) {
		boolean retValue = isNeighbour(to);
		retValue = ( retValue ? onExit() : false );
		retValue = ( retValue ? to.onEnter() : false );
		return retValue;
	}
	
	public boolean isNeighbour(Cell neighbour) {
		boolean retValue = true;
		if( X != neighbour.X && Y != neighbour.Y ) {
			// We are not on the same X or Y path, cannot be neighbours.
			Log.e("Cell", "Not on the same X or Y path, not neighbours (" + this + ", " + neighbour + ").");
			retValue = false;
		} else if( 1 != Math.abs(X - neighbour.X) && 1 != Math.abs(Y - neighbour.Y) ) {
			// We are not one step away from the other path.
			Log.e("Cell", "Not one step away from the next cell, not enighbours (" + this + ", " + neighbour + ").");
			retValue = false;
		}
		return retValue;
	}
	
	@Override
	public String toString() {
		return "Cell{X:" + X + ", Y:" + Y + ",Color:" + color + "}";
	}
	
}
