package de.hpi.javaide.breakout.elements;

import java.awt.geom.Rectangle2D;

import de.hpi.javaide.breakout.Collidable;
import de.hpi.javaide.breakout.starter.Game;

public final class CollisionLogic {
	/**
	 * The constructor of this class is private to make sure that it is only used as a static class.
	 * - it cannot be instantiated,
	 * - it cannot hold a state,
	 * - it contains only static methods
	 */
	private CollisionLogic() { }
	
	/**
	 * This method provides a way to determine if the ball collides with any of the collidable elements on the screen.
	 * Paddle, Bricks, ...
	 * 
	 * @param game
	 * @param ball
	 * @param paddle
	 * @param wall
	 */
	public static void checkCollision(final Game game, final Ball ball, final Paddle paddle, final Wall wall) {
		// check collision with border
		checkCollisionWithBorder(ball);
		// check collision between ball and paddle
		if (checkCollisionWithCollidable(ball, paddle)) {
			ball.bounceY();
			System.out.println("Collision with paddle");
		}
		
		// check collision between ball and every brick in the wall
		for (Brick brick:wall) {
			if (checkCollisionWithCollidable(ball, brick)) {
				boolean bounceBallX = false;
				boolean bounceBallY = false;
				brick.nextStatus();
				System.out.print("Ball: "
						+ ball.getX() + ","
						+ ball.getY()
						+ "  Brick: "
						+ brick.getX() + ","
						+ brick.getY() + "  ");
				System.out.println("Collision with brick");
				if (brick.isDead()) {
					wall.removeBrick(brick);
				}
				if (ballTouchesBrickFromLowerSide(ball, brick)) {
					System.out.println("Lower side");
					bounceBallY = true;
				}
				if (ballTouchesBrickFromUpperSide(ball, brick)) {
					System.out.println("Upper side");
					bounceBallY = true;
				}
				if (ballTouchesBrickFromLeftSide(ball, brick)) {
					System.out.println("Left side");
					bounceBallX = true;
				}
				if (ballTouchesBrickFromRightSide(ball, brick)) {
					System.out.println("Right side");
					bounceBallX = true;
				}
				if (bounceBallX) {
					bounceBallX = false;
//					bounceBallY = false;
					ball.bounceX();
				} else if (bounceBallY) {
					bounceBallY = false;
					ball.bounceY(); 
				}
				break;
			}
		}
		System.out.print("Ball: "
				+ ball.getX() + ","
				+ ball.getY()
				+ "  Paddle: " + paddle.getX()
				+ "," + paddle.getY() + "  ");
	}

	private static boolean checkCollisionWithCollidable(final Collidable ball, final Collidable collidable) {
		if (ball.getGeometry().intersects((Rectangle2D) collidable.getGeometry())) {
			return true;
		}
		return false;
	}

	public static boolean checkCollisionWithLowerBorder(final Ball ball) {
		// check collision with lower border
		if ((ball.getY() - ball.getHeight() / 2) >= Game.SCREEN_Y) {
			return true;
		}
		return false;
	}

	private static void checkCollisionWithBorder(final Ball ball) {
		// check collision with top border
		if ((ball.getY() - ball.getRadius()) < 0) {
			ball.bounceY();
		}

		// check collision with left border
		if ((ball.getX() - ball.getRadius()) < 0) {
			ball.bounceX();
		}

		// check collision with right border
		if ((ball.getX() + ball.getRadius()) >= Game.SCREEN_X) {
			ball.bounceX();
		}
	}
	
	private static boolean ballTouchesBrickFromRightSide(final Ball ball, final Brick brick) {
		if (ballIsTouchingRightBorderOfC(ball, brick)		// right side of brick
				&& (ball.getSpeedX() < 0)) {		// ball moves to the left
			return true;
		}
		return false;
	}
	
	private static boolean ballTouchesBrickFromLeftSide(final Ball ball, final Brick brick) {
		if (ballIsTouchingLeftBorderOfC(ball, brick)	// left side of the brick
				&& (ball.getSpeedX() > 0)) {		// ball moves to the right
			return true;
		}
		return false;
	}
	
	private static boolean ballTouchesBrickFromLowerSide(final Ball ball, final Brick brick) {
		if (ballIsTouchingLowerBorderOfC(ball, brick) &&		// lower side of brick
				(ball.getSpeedY() < 0)) {		// ball moves upwards
			return true;
		}
		return false;
	}
	
	private static boolean ballTouchesBrickFromUpperSide(final Ball ball, final Brick brick) {
		if (ballIsTouchingUpperBorderOfC(ball, brick) &&		// upper side of brick
				(ball.getSpeedY() > 0)) {		// ball moves downwards
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method to check whether ball touches left border of paddle or brick
	 * 
	 * @param ball		Ball
	 * @param c			Collidable Objekt (Paddle oder Brick)
	 * @return
	 */
	private static boolean ballIsTouchingLeftBorderOfC(final Collidable ball, final Collidable c) {
		boolean returnValue = Math.abs((ball.getX() + ball.getWidth() / 2) - (c.getX() - c.getWidth() / 2)) < 5;
		return returnValue;
	}
	
	
	/**
	 * Method to check whether ball touches right border of paddle or brick
	 * 
	 * @param ball		Ball
	 * @param c			Collidable Objekt (Paddle oder Brick)
	 * @return
	 */
	private static boolean ballIsTouchingRightBorderOfC(final Collidable ball, final Collidable c) {
		boolean returnValue = Math.abs((ball.getX() - ball.getWidth() / 2) - (c.getX() + c.getWidth() / 2)) < 5;
		return returnValue;
	}
	
	
	/**
	 * Method to check whether ball touches lower border of brick
	 * 
	 * @param ball		Ball
	 * @param c			Collidable Objekt (Brick)
	 * @return
	 */
	private static boolean ballIsTouchingLowerBorderOfC(final Collidable ball, final Collidable c) {
		boolean returnValue = Math.abs((ball.getY() - ball.getHeight() / 2) - (c.getY() + c.getHeight() / 2)) < 5;
		return returnValue;
	}
	
	/**
	 * Method to check whether ball touches upper border of paddle or brick
	 * 
	 * @param ball		Ball
	 * @param c			Collidable Objekt (Paddle oder Brick)
	 * @return
	 */
	private static boolean ballIsTouchingUpperBorderOfC(final Collidable ball, final Collidable c) {
		boolean returnValue = Math.abs((ball.getY() + ball.getHeight() / 2)	- (c.getY() - c.getHeight() / 2)) < 5;
		return returnValue;
	}
}
