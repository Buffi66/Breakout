package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.elements.*;
import de.hpi.javaide.breakout.elements.ui.Score;
import de.hpi.javaide.breakout.elements.ui.Timer;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public final class GameScreen implements Screen {

	private static Screen instance;

	private BallDepot ballDepot;
	private Ball currentBall;

	private Paddle paddle;
	private Wall wall;

	private Score score;
	private Timer timer;

	private Game game;
	
	private GameScreen(final Game game) {
		this.game = game;
		init();
	}

	/**
	 * GameScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
	 * This approach is not "Thread safe", but is sufficient for our current needs.
	 *
	 * Please, be aware that Singletons need to be handled with care.
	 * There are various ways to implement them, all have there pros and cons.
	 * In his book, Effective Java, Joshua Bloch recommends to create Singletons using an enum, 
	 * which is a language concept that we have not discussed here so far.
	 * For those of you who want to go further we suggest to follow this recommendation 
	 * at some point of time. 
	 * 
	 * @return the EndScreen
	 */
	public static Screen getInstance(final Game game) {
		if (instance == null) {
			instance = new GameScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/*
	 * Hint for the following error messages: 
	 * rather consider to create the necessary constructors than to remove the arguments.
	 * 
	 * (non-Javadoc)
	 * @see de.hpi.javaide.breakout.Initializable#init()
	 */
	@Override
	public void init() {
		ballDepot = new BallDepot(game);
		paddle = new Paddle(game);
		wall = new Wall(game, GameConstants.BRICKS_ROWS, GameConstants.BRICKS_COLUMNS);
		score = new Score(game);
		timer = new Timer(game);
		game.loop();
	}

	@Override
	public void update() {
		if (currentBall == null) {
			// there are no more balls in the game and the depot is empty.
			if (ballDepot.isEmpty()) {
				ScreenManager.setScreen(game, Screen.END);
			}
		} else {
			currentBall.move();
			CollisionLogic.checkCollision(game, currentBall, paddle, wall);
			if (CollisionLogic.checkCollisionWithLowerBorder(currentBall)) {
				currentBall = null;
				timer.pause();
			}
			if (wall.getNoOfBricks() == 0) {
				wall = new Wall(game, GameConstants.BRICKS_ROWS, GameConstants.BRICKS_COLUMNS);
				currentBall.moveToStartPosition();
			}
			if (timer.getTimerWasModuloTen()) {
				currentBall.increaseSpeedX(1);
				currentBall.increaseSpeedY(1);
			}
		}
		timer.update(null);
	}

	@Override
	public void display() {
		ballDepot.display();
		if (currentBall != null) {
			currentBall.display();
		}
		paddle.display();
		wall.display();
		score.display();
		timer.display();
	}

	@Override
	public void handleKeyPressed(final String key) {
		switch (key) {
		case Screen.KEY_ENTER:
			currentBall = ballDepot.dispense();
			currentBall.setSpeedX(Ball.INITIAL_SPEED);
			currentBall.setSpeedY(Ball.INITIAL_SPEED);
			timer.reset();
			break;
		case Screen.KEY_SPACE:
		default:
			break;
		}
	}

	@Override
	public void handleMouseDragged() {
		paddle.move();
	}

	@Override
	public void increaseScore(final int amount) {
		// (Hint: the update() Method expects an input argument of type String)
		score.update(Integer.toString(amount));
	}
}
