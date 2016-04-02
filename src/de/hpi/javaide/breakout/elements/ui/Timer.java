package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class Timer extends UIObject {
	
	private int startMillis;
	private int millisLeft;
	private boolean timerIsRunning;
	private boolean timerWasModuloTen;
	private static final int POS_X = Game.SCREEN_X - 150;
	private static final int POS_Y = Game.SCREEN_Y - 70;
	private static final int MILLIS_LEFT_START = 60000;

	public Timer(final Game game) {
		super(game);
		millisLeft = MILLIS_LEFT_START;
		timerIsRunning = false;
	}

	@Override
	public final void display() {
		game.fill(GameConstants.COLOR);
		game.textFont(Font.getFont16());
		game.text("Time left: " + getSecondsLeft(), 
				POS_X, POS_Y);
	}

	@Override
	public final void update(final String input) {
		if (timerIsRunning) {
			millisLeft = startMillis - game.millis();
			if (millisLeft < 0) {
				millisLeft = 0;
			}
		}
	}
	
	public final void reset() {
		millisLeft = 60000;
		startMillis = millisLeft + game.millis();
		timerIsRunning = true;
	}
	
	public final void pause() {
		timerIsRunning = false;
	}
	
	private int getSecondsLeft() {
		return millisLeft / 1000;
	}

	public final boolean getTimerWasModuloTen() {
		if (!timerWasModuloTen && (getSecondsLeft() % 10) ==  0) {
			timerWasModuloTen = true;
			return true;
		}
		if (getSecondsLeft() % 10 != 0) {
			timerWasModuloTen = false;
		}
		return false;
	}
}
