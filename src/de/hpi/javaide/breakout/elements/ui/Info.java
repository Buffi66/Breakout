package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PApplet;

public class Info extends UIObject {

	private String content;
	
	public Info(Game game, String content) {
		super(game);
		this.content = content;
	}

	@Override
	public void display() {
		game.fill(255);
	    game.textFont(Font.getFont32());
	    game.textAlign(PApplet.CENTER, PApplet.CENTER);
	    game.text(content, game.width / 2, game.height / 2);
	}

	@Override
	public void update(String input) {
		// TODO Auto-generated method stub
		
	}
}
