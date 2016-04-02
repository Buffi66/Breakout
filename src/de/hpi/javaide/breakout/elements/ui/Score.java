package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

public class Score extends UIObject {

	private static int score = 0;
	
	public Score(Game game) {
		super(game);	
	}

	@Override
	public void display() {
		game.fill(255);
		game.textFont(Font.getFont16());
		game.text("Score: " + score, 150, game.height-80);
	}

	@Override
	public void update(String input) {
		// TODO Auto-generated method stub
		score += Integer.parseInt(input);
	}
	
	public static int getScore() {
		return score;
	}
}
