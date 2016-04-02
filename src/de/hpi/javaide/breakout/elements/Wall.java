package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.basics.Color;
import de.hpi.javaide.breakout.starter.Game;

public class Wall implements Displayable, Iterable<Brick> {

	private ArrayList<Brick> wall;

	private int brickWidth;
	final private int DISTANCE_BETWEEN_BRICKS = 10;
	final private int DISTANCE_FROM_BORDER = 100;
	private int height;
	private int distanceY;
	private Color[] color;

	public Wall(final Game game, final int i, final int j) {
		wall = new ArrayList<Brick>();
		distanceY = 6; // 6 px
		brickWidth = (Game.SCREEN_X
				- 2 * DISTANCE_FROM_BORDER
				- DISTANCE_BETWEEN_BRICKS * (i - 1)) / i;

		height = Game.SCREEN_Y/j/3-distanceY;
		color = new Color[j];
		for (int rowColor = 0; rowColor < j; rowColor++) {
			color[rowColor] = new Color(205, 50, 50); 
//			color[rowColor] = new Color(105 + rowColor * 20, 105 + rowColor * 20, 105 + rowColor * 20); 
		}

		buildWall(game, i, j);
	}
	
	// iterates over copy of wall, so that removing a brick will not cause an exception
	@Override
	public final Iterator<Brick> iterator() {
		return new ArrayList<Brick>(wall).iterator();
	}
	
	/**
	 * Build the wall by putting the single bricks into their position.
	 * Hint: You might want to use one or two for-loops
	 * 
	 * @param game    game
	 * @param columns Anzahl der Spalten
	 * @param rows    Anzahl der Zeilen
	 */
	private void buildWall(final Game game, final int columns, final int rows) {
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Brick brick = new Brick(game, 
					new Point(DISTANCE_FROM_BORDER + brickWidth*column + brickWidth/2 + DISTANCE_BETWEEN_BRICKS*(column-1), 
						row * (height + distanceY) + height / 2 + distanceY), 
					new Dimension(brickWidth, height), 
					color[row],
					10 * (rows - row));
				wall.add(brick);
			}
		}
	}
	
	@Override
	public final void display() {
		for (Brick brick:wall) {
			brick.display();
		}
	}
	
	// removes brick from wall
	public final void removeBrick(final Brick brick) {
		wall.remove(brick);
	}
	
	public final int getNoOfBricks() {
		return wall.size();
	}
}
