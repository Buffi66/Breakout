package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Elliptic;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PApplet;

public class Ball extends Elliptic {
	
	private int speedX;
	private int speedY;
	private int movesSinceLastDirectionChangeX;
	private int movesSinceLastDirectionChangeY;
	public static final int SIZE = 15;
	private static final int MOVES_SINCE_LAST_DIRECTION_CHANGE = 5;
	public static final int INITIAL_SPEED = 2;
	
	public Ball(final Game game, final Point position) {
		super(game, position, new Dimension(SIZE, SIZE));
		movesSinceLastDirectionChangeX = 0;
		movesSinceLastDirectionChangeY = 0;
		speedX = INITIAL_SPEED;
		speedY = INITIAL_SPEED;
	}

	public final void move() {
		int xValue = getX() + speedX;
		if (xValue < 0) {
			xValue = 0;
		}
		if (xValue > game.displayWidth) {
			xValue = game.displayWidth;
		}
		int yValue = getY() + speedY;
		if (yValue < 0) {
			yValue = 0;
		}
		if (yValue > game.displayHeight) {
			yValue = game.displayHeight;
		}
		update(new Point(xValue, yValue), 
				new Dimension(getWidth(), getHeight()));
		System.out.println("movesSinceX: " + movesSinceLastDirectionChangeX
				+ "movesSinceY: " + movesSinceLastDirectionChangeY
				+ "  speedX: " + speedX 
				+ "  speedY: " + speedY);
		movesSinceLastDirectionChangeX++;
		movesSinceLastDirectionChangeY++;
	}

	@Override
	public final void display() {
		game.rectMode(PApplet.CENTER);
		game.noStroke();
		game.fill(getR(), getG(), getB());
		game.ellipse(getX(), getY(), getWidth(), getHeight());
	}
	
	public final int getRadius() {
		return getWidth() / 2;
	}

	public final int getSpeedX() {
		return speedX;
	}

	public final int getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(int speed) {
		if (speed >= 0) {
			speedX = speed;
		}
	}
	
	public void setSpeedY(int speed) {
		if (speed >= 0) {
			speedY = speed;
		}
	}

	public final void increaseSpeedX(final int increase) {
		if (speedX > 0) {
			speedX += increase;
		} else {
			speedX -= increase;
		}
	}

	public final void increaseSpeedY(final int increase) {
		if (speedY > 0) {
			speedY += increase;
		} else {
			speedY -= increase;
		}
	}
	
	public final void bounceX() {
		if (movesSinceLastDirectionChangeX > MOVES_SINCE_LAST_DIRECTION_CHANGE) {
			speedX = -1 * speedX;
			movesSinceLastDirectionChangeX = 0;
		}
	}
	
	public final void bounceY() {
		if (movesSinceLastDirectionChangeY > MOVES_SINCE_LAST_DIRECTION_CHANGE) {
			speedY = -1 * speedY;
			movesSinceLastDirectionChangeY = 0;
		}
	}
	
	public final void moveToStartPosition() {
		update(new Point(Game.SCREEN_X / 2, Game.SCREEN_Y / 2), 
				new Dimension(getWidth(), getHeight()));
	}
}
