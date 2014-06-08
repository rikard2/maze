package com.boss.maze.test;

import org.andengine.util.color.Color;

import com.boss.maze.Cell;

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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
		Cell n = new Cell(0, 1);
		Cell ne = new Cell(0, 2);
		Cell e = new Cell(1, 2);
		Cell se = new Cell(2, 2);
		Cell s = new Cell(2, 1);
		Cell sw = new Cell(2, 0);
		Cell w = new Cell(1, 0);
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
	
}
