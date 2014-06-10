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
	
	/**
	 * Constrcutor
	 * @param x int with the X coordinate.
	 * @param y int with the Y coordinate.
	 */
	public Cell(int x, int y) {
		color = green;
		X = x;
		Y = y;
	}
	
	/**
	 * Method to check when entering the cell, will do a state change on the color if successful.
	 * @return boolean
	 * @throws IllegalStateException if an unknown color is assigned to the cell.
	 */
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
	
	/**
	 * Method to check when exiting a cell, will do a stat change on the color if successful.
	 * @return boolean
	 * @throws IllegalStateException if an unknown color is assigned to the cell.
	 */
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
	
	/**
	 * Method to move from one cell to another.
	 * @brief Method evaluates if the cells are neighbours, can move according to open params and 
	 * that each cell is in states allowing a move.
	 * @param to The Cell to move to.
	 * @return boolean with success or fail of the move.
	 */
	public boolean moveTo(Cell to) {
		boolean retValue = openPath(to);
		retValue = ( retValue ? onExit() : false );
		retValue = ( retValue ? to.onEnter() : false );
		return retValue;
	}
	
	/**
	 * Method to check if a cell is neightbour to another cell.
	 * @param neighbour Cell to check if neighbouring to.
	 * @return boolean
	 */
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
	
	/**
	 * Method to get the compass direction from one cell to another.
	 * @brief Expects the cells to be neighbours. Will only evaluate North, East, 
	 * South and West. Returns Undefined if no direction is found.
	 * @param toCheck Cell to check direction against.
	 * @return Direction enum
	 */
	public Direction getDirectionTo(Cell toCheck) {
		if( true != isNeighbour(toCheck) ) {
			return Direction.UNDEFINED;
		} else {
			if( 1 == (X - toCheck.X) ) {
				return Direction.WEST;
			} else if ( -1 == (X - toCheck.X) ) {
				return Direction.EAST;
			} else if ( 1 == (Y - toCheck.Y) ) {
				return Direction.NORTH;
			} else {
				return Direction.SOUTH;
			}
		}
	}
	
	/**
	 * Method to evaluate if there is an open path between 2 cells.
	 * @param to Cell to evaluate towards.
	 * @return boolean
	 */
	public boolean openPath(Cell to) {
		Direction direction = getDirectionTo(to);
		boolean retValue;
		switch( direction ) {
			case WEST: {
				retValue = (openLeft && to.openRight);
				break;
			}
			case EAST: {
				retValue = (openRight && to.openLeft);
				break;
			}
			case NORTH: {
				retValue = (openTop && to.openBottom);
				break;
			}
			case SOUTH: {
				retValue = (openBottom && to.openTop);
				break;
			}
			case UNDEFINED:
			default: {
				retValue = false;
			}
		}
		return retValue;
	}
	
	@Override
	public String toString() {
		return "Cell{X:" + X + ", Y:" + Y + ",Color:" + color + "}";
	}
	
}
