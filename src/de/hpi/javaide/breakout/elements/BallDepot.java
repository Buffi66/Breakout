package de.hpi.javaide.breakout.elements;

import java.util.ArrayList;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.Measureable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class BallDepot implements Displayable, Measureable {

	private ArrayList<Ball> balls;
	
	public BallDepot(Game game) {
		// TODO Auto-generated constructor stub
		balls = new ArrayList<Ball>();
		for (int i = 0; i < GameConstants.LIVES; i++) {
			balls.add(new Ball(game, GameConstants.STARTPOSITION));
		}
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	public Ball dispense() {
		// TODO Auto-generated method stub
		if (balls.size() > 0) {
			Ball ball = balls.get(balls.size()-1);
			balls.remove(ball);
			return ball;
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (balls.size() == 0);
	}
}
