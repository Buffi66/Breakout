package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import processing.core.PApplet;
import de.hpi.javaide.breakout.basics.Color;
import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class Brick extends Rectangular {
	
	private int hitCounter;
	private int score;
	
	public Brick(Game game, Point position, Dimension dimension, Color color, int score) {
		super(game, position, dimension);
		this.score = score;
		hitCounter = GameConstants.HITS;
		setColor(color.getR(), color.getG(), color.getB());
	}
	
	@Override
	public void display() {
		game.rectMode(PApplet.CENTER);
		game.noStroke();
		if (isDead()) {
			// bricks which are dead should be invisible, resp. should have the same color than background
			game.fill(0, 0, 0);
		} else {
			game.fill(getR(), getG(), getB());
			int a = 0;
		}
		game.rect(getX(), getY(), getWidth(), getHeight());
	}
	
	public void nextStatus() {
		if (hitCounter >= 0) {
			hitCounter--;
			game.increaseScore(score);
			setColor(getR()-50, getG()-50,getB()-50);
		}
	}
	
	public boolean isDead() {
		return(hitCounter==0);
	}
}
