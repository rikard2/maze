package com.boss.maze.test;

import org.andengine.util.color.Color;

import com.boss.maze.Cell;
import com.boss.maze.Direction;

import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

public class CellTest extends TestCase {

	@SmallTest
	public void testConstructor() {
		Cell testObject = new Cell(25, 26);
		assertTrue(testObject.openBottom);
		assertTrue(testObject.openLeft);
		assertTrue(testObject.openRight);
		assertTrue(testObject.openTop);
		assertEquals(testObject.green, testObject.color);
		assertEquals(25, testObject.X);
		assertEquals(26, testObject.Y);
	}
	
	@SmallTest
	public void testOnEnter() {
		Cell testObject = new Cell(25, 26);
		assertTrue(testObject.onEnter());
		assertEquals(testObject.yellow, testObject.color);
		assertFalse(testObject.onEnter());
		assertEquals(testObject.yellow, testObject.color);
		testObject.color = Color.WHITE;
		boolean catched = false;
		try {
			testObject.onEnter();
		} catch (IllegalStateException e) {
			catched = true;
		} finally {
			assertTrue(catched);
		}
	}
	
	@SmallTest
	public void testOnExit() {
		Cell testObject = new Cell(25, 26);
		assertFalse(testObject.onExit());
		assertTrue(testObject.onEnter());
		assertTrue(testObject.onExit());
		assertEquals(testObject.red, testObject.color);
		assertFalse(testObject.onExit());
		assertEquals(testObject.red, testObject.color);
		testObject.color = Color.WHITE;
		boolean catched = false;
		try {
			testObject.onExit();
		} catch (IllegalStateException e) {
			catched = true;
		} finally {
			assertTrue(catched);
		}
	}
	
	@SmallTest
	public void testIsNeighbourNW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(nw.isNeighbour(nw));
		assertTrue(nw.isNeighbour(n));
		assertFalse(nw.isNeighbour(ne));
		assertFalse(nw.isNeighbour(e));
		assertFalse(nw.isNeighbour(se));
		assertFalse(nw.isNeighbour(s));
		assertFalse(nw.isNeighbour(sw));
		assertTrue(nw.isNeighbour(w));
		assertFalse(nw.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourN() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertTrue(n.isNeighbour(nw));
		assertFalse(n.isNeighbour(n));
		assertTrue(n.isNeighbour(ne));
		assertFalse(n.isNeighbour(e));
		assertFalse(n.isNeighbour(se));
		assertFalse(n.isNeighbour(s));
		assertFalse(n.isNeighbour(sw));
		assertFalse(n.isNeighbour(w));
		assertTrue(n.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourNE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(ne.isNeighbour(nw));
		assertTrue(ne.isNeighbour(n));
		assertFalse(ne.isNeighbour(ne));
		assertTrue(ne.isNeighbour(e));
		assertFalse(ne.isNeighbour(se));
		assertFalse(ne.isNeighbour(s));
		assertFalse(ne.isNeighbour(sw));
		assertFalse(ne.isNeighbour(w));
		assertFalse(ne.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(e.isNeighbour(nw));
		assertFalse(e.isNeighbour(n));
		assertTrue(e.isNeighbour(ne));
		assertFalse(e.isNeighbour(e));
		assertTrue(e.isNeighbour(se));
		assertFalse(e.isNeighbour(s));
		assertFalse(e.isNeighbour(sw));
		assertFalse(e.isNeighbour(w));
		assertTrue(e.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourSE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(se.isNeighbour(nw));
		assertFalse(se.isNeighbour(n));
		assertFalse(se.isNeighbour(ne));
		assertTrue(se.isNeighbour(e));
		assertFalse(se.isNeighbour(se));
		assertTrue(se.isNeighbour(s));
		assertFalse(se.isNeighbour(sw));
		assertFalse(se.isNeighbour(w));
		assertFalse(se.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourS() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(s.isNeighbour(nw));
		assertFalse(s.isNeighbour(n));
		assertFalse(s.isNeighbour(ne));
		assertFalse(s.isNeighbour(e));
		assertTrue(s.isNeighbour(se));
		assertFalse(s.isNeighbour(s));
		assertTrue(s.isNeighbour(sw));
		assertFalse(s.isNeighbour(w));
		assertTrue(s.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourSW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(sw.isNeighbour(nw));
		assertFalse(sw.isNeighbour(n));
		assertFalse(sw.isNeighbour(ne));
		assertFalse(sw.isNeighbour(e));
		assertFalse(sw.isNeighbour(se));
		assertTrue(sw.isNeighbour(s));
		assertFalse(sw.isNeighbour(sw));
		assertTrue(sw.isNeighbour(w));
		assertFalse(sw.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertTrue(w.isNeighbour(nw));
		assertFalse(w.isNeighbour(n));
		assertFalse(w.isNeighbour(ne));
		assertFalse(w.isNeighbour(e));
		assertFalse(w.isNeighbour(se));
		assertFalse(w.isNeighbour(s));
		assertTrue(w.isNeighbour(sw));
		assertFalse(w.isNeighbour(w));
		assertTrue(w.isNeighbour(middle));
	}
	
	@SmallTest
	public void testIsNeighbourMiddle() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertFalse(middle.isNeighbour(nw));
		assertTrue(middle.isNeighbour(n));
		assertFalse(middle.isNeighbour(ne));
		assertTrue(middle.isNeighbour(e));
		assertFalse(middle.isNeighbour(se));
		assertTrue(middle.isNeighbour(s));
		assertFalse(middle.isNeighbour(sw));
		assertTrue(middle.isNeighbour(w));
		assertFalse(middle.isNeighbour(middle));
	}
	
	@SmallTest
	public void testGetDirectionToNW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(nw));
		assertEquals(Direction.EAST, nw.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(sw));
		assertEquals(Direction.SOUTH, nw.getDirectionTo(w));
		assertEquals(Direction.UNDEFINED, nw.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToN() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.WEST, n.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(n));
		assertEquals(Direction.EAST, n.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, n.getDirectionTo(w));
		assertEquals(Direction.SOUTH, n.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToNE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(nw));
		assertEquals(Direction.WEST, ne.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(ne));
		assertEquals(Direction.SOUTH, ne.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(w));
		assertEquals(Direction.UNDEFINED, ne.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(n));
		assertEquals(Direction.NORTH, e.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(e));
		assertEquals(Direction.SOUTH, e.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, e.getDirectionTo(w));
		assertEquals(Direction.WEST, e.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToSE() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(ne));
		assertEquals(Direction.NORTH, se.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(se));
		assertEquals(Direction.WEST, se.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(w));
		assertEquals(Direction.UNDEFINED, se.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToS() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(e));
		assertEquals(Direction.EAST, s.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(s));
		assertEquals(Direction.WEST, s.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, s.getDirectionTo(w));
		assertEquals(Direction.NORTH, s.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToSW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(se));
		assertEquals(Direction.EAST, sw.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(sw));
		assertEquals(Direction.NORTH, sw.getDirectionTo(w));
		assertEquals(Direction.UNDEFINED, sw.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToW() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.NORTH, w.getDirectionTo(nw));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(ne));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(se));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(s));
		assertEquals(Direction.SOUTH, w.getDirectionTo(sw));
		assertEquals(Direction.UNDEFINED, w.getDirectionTo(w));
		assertEquals(Direction.EAST, w.getDirectionTo(middle));
	}
	
	@SmallTest
	public void testGetDirectionToMiddle() {
		Cell nw = new Cell(0, 0);
		Cell n = new Cell(1, 0);
		Cell ne = new Cell(2, 0);
		Cell e = new Cell(2, 1);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(1, 2);
		Cell sw = new Cell(0, 2);
		Cell w = new Cell(0, 1);
		Cell middle = new Cell(1, 1);
		assertEquals(Direction.UNDEFINED, middle.getDirectionTo(nw));
		assertEquals(Direction.NORTH, middle.getDirectionTo(n));
		assertEquals(Direction.UNDEFINED, middle.getDirectionTo(ne));
		assertEquals(Direction.EAST, middle.getDirectionTo(e));
		assertEquals(Direction.UNDEFINED, middle.getDirectionTo(se));
		assertEquals(Direction.SOUTH, middle.getDirectionTo(s));
		assertEquals(Direction.UNDEFINED, middle.getDirectionTo(sw));
		assertEquals(Direction.WEST, middle.getDirectionTo(w));
		assertEquals(Direction.UNDEFINED, middle.getDirectionTo(middle));
	}
	
}
