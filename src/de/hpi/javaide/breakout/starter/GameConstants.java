package de.hpi.javaide.breakout.starter;

import java.awt.Point;

public interface GameConstants {
	int LIVES = 3;
	int HITS = 3;
	int SCREEN_X = 1280;
	int SCREEN_Y = 720;
	int BRICKS_ROWS = 6;
	int BRICKS_COLUMNS = 7;
	Point STARTPOSITION = new Point(SCREEN_X/2, SCREEN_Y/2);
}
