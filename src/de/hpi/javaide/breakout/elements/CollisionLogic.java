package de.hpi.javaide.breakout.elements;

import de.hpi.javaide.breakout.Collidable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

final public class CollisionLogic {
	/**
	 * The constructor of this class is private to make sure that it is only used as a static class.
	 * - it cannot be instantiated,
	 * - it cannot hold a state,
	 * - it contains only static methods
	 */
	private CollisionLogic() {}
	
	/**
	 * This method provides a way to determine if the ball collides with any of the collidable elements on the screen.
	 * Paddle, Bricks, ...
	 * 
	 * @param game
	 * @param ball
	 * @param paddle
	 * @param wall
	 */
	public static void checkCollision(Game game, Ball ball, Paddle paddle, Wall wall) {
		// check collision with border
		checkCollisionwithBorder(game, ball);
		// check collision between ball and paddle
		if (checkCollisionWithPaddle(ball, paddle)) {
			ball.bounceY();
			System.out.println("Collision with paddle");
		}
		
		// check collision between ball and every brick in the wall
		for (Brick brick:wall) {
			if (ballTouchesBrick(ball, brick)) {
				boolean bounceBallX = false;
				boolean bounceBallY = false;
				brick.nextStatus();
				System.out.print("Ball: " + ball.getX() + "," + ball.getY() + "  Brick: " + brick.getX() + "," + brick.getY() + "  ");
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
					bounceBallY = false;
					ball.bounceX();
				} else if(bounceBallY) {
					bounceBallY = false;
					ball.bounceY(); 
				}
				break;
			}
		}
		System.out.print("Ball: " + ball.getX() + "," + ball.getY() + "  Paddle: " + paddle.getX() + "," + paddle.getY() + "  ");
	}

	private static boolean checkCollisionWithPaddle(Collidable ball, Collidable paddle) {
		if (isRightSideOfC1ToTheRightOfLeftSideOfC2(ball, paddle) &&		// left side of paddle
				isLeftSideOfC1ToTheLeftOfRightSideOfC2(ball, paddle) &&	// right side of paddle
				isLowerSideOfC1BelowUpperSideOfC2(ball, paddle)) {			// lower side of paddle
			return true;
		} else {
			return false;
		}
	}

	public static Ball checkCollisionwithLowerBorder(Ball ball) {
		// check collision with lower border
		if ((ball.getY() - ball.getHeight()/2) >= GameConstants.SCREEN_Y) {
			return null;
		} else {
			return ball;
		}
	}

	private static void checkCollisionwithBorder(Game game, Ball ball) {
		// check collision with top border
		if ((ball.getY() - ball.getHeight()/2) < 0) {
			ball.bounceY();
		}

		// check collision with left border
		if ((ball.getX() - ball.getWidth()/2) < 0) {
			ball.bounceX();
		}

		// check collision with right border
		if ((ball.getX() + ball.getWidth()/2) >= GameConstants.SCREEN_X) {
			ball.bounceX();
		}
	}
	
	private static boolean ballTouchesBrick(Ball ball, Brick brick) {
		if (isRightSideOfC1ToTheRightOfLeftSideOfC2(ball, brick) &&		// left side of brick
				isLeftSideOfC1ToTheLeftOfRightSideOfC2(ball, brick) &&		// right side of brick
				isLowerSideOfC1BelowUpperSideOfC2(ball, brick) &&	// top side of brick
				isUpperSideOfBallOverLowerSideOfC2(ball, brick)) {		// lower side of brick
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean ballTouchesBrickFromRightSide(Ball ball, Brick brick) {
		if (ballIsTouchingRightBorderOfC2(ball, brick) &&			// right side of brick
				isLowerSideOfC1BelowUpperSideOfC2(ball, brick) &&		// top side of brick
				isUpperSideOfBallOverLowerSideOfC2(ball, brick) &&		// lower side of brick
				(ball.getIncreaseX() < 0)) {		// ball moves to the left
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean ballTouchesBrickFromLeftSide(Ball ball, Brick brick) {
		if (ballIsTouchingLeftBorderOfC2(ball, brick) &&			// left side of brick
				isLowerSideOfC1BelowUpperSideOfC2(ball, brick) &&		// top side of brick
				isUpperSideOfBallOverLowerSideOfC2(ball, brick) &&		// lower side of brick
				(ball.getIncreaseX() > 0)) {		// ball moves to the right
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean ballTouchesBrickFromLowerSide(Ball ball, Brick brick) {
		if (isLeftSideOfC1ToTheLeftOfRightSideOfC2(ball, brick) &&			// right side of brick
				isRightSideOfC1ToTheRightOfLeftSideOfC2(ball, brick) &&		// left side of brick
				ballIsTouchingLowerBorderOfC2(ball, brick) &&		// 
				(ball.getIncreaseY() < 0)) {		// ball moves upwards
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean ballTouchesBrickFromUpperSide(Ball ball, Brick brick) {
		if (isLeftSideOfC1ToTheLeftOfRightSideOfC2(ball, brick) &&			// right side of brick
				isRightSideOfC1ToTheRightOfLeftSideOfC2(ball, brick) &&		// left side of brick
				ballIsTouchingUpperBorderOfC2(ball, brick) &&		// top side of brick
				(ball.getIncreaseY() > 0)) {		// ball moves downwards
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isRightSideOfC1ToTheRightOfLeftSideOfC2(Collidable c1, Collidable c2) {
		return(c1.getX() + c1.getWidth()/2) > (c2.getX() - c2.getWidth()/2);
	}
	
	private static boolean isLeftSideOfC1ToTheLeftOfRightSideOfC2(Collidable c1, Collidable c2) {
		return(c1.getX() - c1.getWidth()/2) < (c2.getX() + c2.getWidth()/2);
	}
	
	private static boolean isLowerSideOfC1BelowUpperSideOfC2(Collidable c1, Collidable c2) {
		return(((c1.getY() + c1.getHeight()/2+1)) > (c2.getY() - c2.getHeight()/2));
	}
	
	private static boolean isUpperSideOfBallOverLowerSideOfC2(Collidable c1, Collidable c2) {
		return(c1.getY() - c1.getHeight()/2) < ((c2.getY() + c2.getHeight()/2));
	}
	
	private static boolean ballIsTouchingLeftBorderOfC2(Collidable ball, Collidable c) {
		boolean returnValue = Math.abs((ball.getX() + ball.getWidth()/2) - (c.getX() - c.getWidth()/2)) < 5;
		return returnValue;
	}
	
	private static boolean ballIsTouchingRightBorderOfC2(Collidable ball, Collidable c) {
		boolean returnValue = Math.abs((ball.getX() - ball.getWidth()/2) - (c.getX() + c.getWidth()/2)) < 5;
		return returnValue;
	}
	
	private static boolean ballIsTouchingLowerBorderOfC2(Collidable ball, Collidable c) {
		boolean returnValue = Math.abs((ball.getY() - ball.getHeight()/2) - (c.getY() + c.getHeight()/2)) < 5;
		return returnValue;
	}
	
	private static boolean ballIsTouchingUpperBorderOfC2(Collidable ball, Collidable c) {
		boolean returnValue = Math.abs((ball.getY() + ball.getHeight()/2) - (c.getY() - c.getHeight()/2)) < 5;
		return returnValue;
	}
}
