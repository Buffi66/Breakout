package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;

import de.hpi.javaide.breakout.Collidable;
import de.hpi.javaide.breakout.Colorable;
import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;

public abstract class CollisionObject implements Collidable, Displayable, Colorable {

	protected Shape geometry;
	protected Game game;
	protected Dimension dimension;
	protected Point position;
	private Color color;

	public CollisionObject(final Game game, final Point position, final Dimension dimension) {
		this.game = game;
		this.position = position;
		this.dimension = dimension;
		this.color = new Color(255, 255, 255);
	}

	@Override
	public final int getWidth() {
		return dimension.width;
	}

	@Override
	public final int getHeight() {
		return dimension.height;
	}

	@Override
	public final int getX() {
		return position.x;
	}

	@Override
	public final int getY() {
		return position.y;
	}

	@Override
	public final int getR() {
		return color.getR();
	}

	@Override
	public final int getG() {
		return color.getG();
	}

	@Override
	public final int getB() {
		return color.getB();
	}

	@Override
	public final void setColor(final int r, final int g, final int b) {
		this.color = new Color(r, g, b);
	}

	@Override
	public final Shape getGeometry() {
		return this.geometry;
	}

	@Override
	public void update(final Point position, final Dimension dimension) {
		this.position = position;
		this.dimension = dimension;
	}
}