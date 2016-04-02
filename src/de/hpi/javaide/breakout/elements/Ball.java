package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import processing.core.PApplet;
import de.hpi.javaide.breakout.basics.Elliptic;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class Ball extends Elliptic {
	
	private int increaseX;
	private int increaseY;
	private int movesSinceLastDirectionChangeX;
	private int movesSinceLastDirectionChangeY;
	final private int movesSinceLastDirectionChangeMin = 5;
	
	public Ball(Game game, Point position) {
		super(game, position, new Dimension(10, 10));
		movesSinceLastDirectionChangeX = 0;
		movesSinceLastDirectionChangeY = 0;
		increaseX = 4;
		increaseY = 4;
	}

	public void move() {
		// TODO Auto-generated method stub
		update(new Point(getX() + increaseX, getY() + increaseY), new Dimension(getWidth(), getHeight()));
		System.out.println("movesSinceX: " + movesSinceLastDirectionChangeX + " movesSinceY: " + movesSinceLastDirectionChangeY + "  increaseX: " + increaseX + "  increaseY: " + increaseY);
		movesSinceLastDirectionChangeX++;
		movesSinceLastDirectionChangeY++;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		game.rectMode(PApplet.CENTER);
		game.noStroke();
		game.fill(getR(), getG(), getB());
		game.ellipse(getX(), getY(), getWidth(), getHeight());
	}

	public int getIncreaseX() {
		return increaseX;
	}

	public void setIncreaseX(int increaseX) {
		this.increaseX = increaseX;
	}

	public int getIncreaseY() {
		return increaseY;
	}

//	public void setIncreaseY(int increaseY) {
//		this.increaseY = increaseY;
//	}
	
	public void bounceX() {
		if (movesSinceLastDirectionChangeX > movesSinceLastDirectionChangeMin) {
			increaseX = -1 * increaseX;
			movesSinceLastDirectionChangeX = 0;
		}
	}
	
	public void bounceY() {
		if (movesSinceLastDirectionChangeY > movesSinceLastDirectionChangeMin) {
			increaseY = -1 * increaseY;
			movesSinceLastDirectionChangeY = 0;
		}
	}
	
	public int getMovesSinceLastDirectionChangeX() {
		return movesSinceLastDirectionChangeX;
	}
	
	public int getMovesSinceLastDirectionChangeY() {
		return movesSinceLastDirectionChangeY;
	}
	
	public int getMovesSinceLastDirectionChangeMin() {
		return movesSinceLastDirectionChangeMin;
	}
	
	public void moveToStartPosition() {
		update(GameConstants.STARTPOSITION, new Dimension(getWidth(), getHeight()));
	}
}
