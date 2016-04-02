package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

public class Score extends UIObject {

	private static int score = 0;
	private static final int POS_X = 100;
	private static final int POS_Y = Game.SCREEN_Y - 70;
	
	public Score(final Game game) {
		super(game);	
	}

	@Override
	public final void display() {
		game.fill(GameConstants.COLOR);
		game.textAlign(PApplet.LEFT);
		game.textFont(Font.getFont16());
		game.text("Score: " + score, POS_X, POS_Y);
	}

	@Override
	public final void update(final String input) {
		score += Integer.parseInt(input);
	}
	
	public static int getScore() {
		return score;
	}
}
