package de.hpi.javaide.breakout.starter;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.screens.Screen;
import de.hpi.javaide.breakout.screens.ScreenManager;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Game extends PApplet implements GameConstants {
	
	public static int SCREEN_X;
	public static int SCREEN_Y;

	// Setup the game
	@Override
	public final void setup() {
//		size(GameConstants.SCREEN_X, GameConstants.SCREEN_Y);
		SCREEN_X = displayWidth - 150;
		SCREEN_Y = displayHeight - 100;
		size(SCREEN_X, SCREEN_Y);
		background(0);
		frameRate(30);
		Font.init(this);
		ScreenManager.setScreen(this, Screen.START);
	}

	// Update and draw everything in the game
	@Override
	public final void draw() {
		background(0);
		ScreenManager.getCurrentScreen().update();
		ScreenManager.getCurrentScreen().display();
	}

	// Interact with the mouse
	@Override
	public void mouseMoved() {
	// mouse move without pressed button should not be of interest
	}

	@Override
	public final void mouseDragged() {
		ScreenManager.getCurrentScreen().handleMouseDragged();
	}

	// Interact with the keyboard
	@Override
	public final void keyPressed() {
		switch (key) {
		case RETURN:
		case ENTER:
			ScreenManager.getCurrentScreen().handleKeyPressed(Screen.KEY_ENTER);
			break;
		default: System.out.println("key:" + key + "/"); break;
		}
	}

	@Override
	public void keyReleased() {

	}

	public final void increaseScore(final int i) {
		ScreenManager.getCurrentScreen().increaseScore(i);
	}
}
