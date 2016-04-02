package de.hpi.javaide.breakout.elements;

import java.awt.Point;
import java.util.ArrayList;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.Measureable;
import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

public class BallDepot implements Displayable, Measureable {

	private ArrayList<Ball> balls;
	private Game game;
	private static final int POS_X = 100;
	private static final int POS_Y = Game.SCREEN_Y - 120;
	
	public BallDepot(final Game game) {
		this.game = game;
		balls = new ArrayList<Ball>();
		for (int i = 0; i < GameConstants.LIVES; i++) {
			balls.add(new Ball(game, 
					new Point(POS_X + 100 +  (Ball.SIZE + 5) * i, 
							POS_Y)));
		}
	}

	@Override
	public final int getX() {
		// not needed; therefore only zero will be returned
		return 0;
	}

	@Override
	public final int getY() {
		// not needed; therefore only zero will be returned
		return 0;
	}

	@Override
	public final int getWidth() {
		// not needed; therefore only zero will be returned
		return 0;
	}

	@Override
	public final int getHeight() {
		// not needed; therefore only zero will be returned
		return 0;
	}

	@Override
	public final void display() {
		game.fill(GameConstants.COLOR);
		game.textAlign(PApplet.LEFT, PApplet.CENTER);
		game.textFont(Font.getFont16());
		game.text("Balls left:", 
				POS_X, POS_Y);
		for (Ball ball:balls) {
			ball.display();
		}
	}

	public final Ball dispense() {
		if (balls.size() > 0) {
			Ball currentBall = balls.remove(0);
			currentBall.moveToStartPosition();
			return currentBall;
		} else {
			return null;
		}
	}

	public final boolean isEmpty() {
		return (balls.size() == 0);
	}
}
