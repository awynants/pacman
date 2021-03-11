package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @invar the number of lives is at least 0
 *   | 0 <= getNbLives()
 * @invar the square that Pac-man is on cannot be null
 * 	 | getSquare() != null
 * @invar the square Pac-man is on must be passable
 * 	 | getSquare().isPassable()
 */
public class PacMan {
	
	/**
	 * @representationObject
	 * This variable represents the number of lives of this Pac-Man character
	 * 
	 * @invar | 0 <= lives
	 */
	private int lives;
	
	/**
	 * @representationObject
	 * This variable represents the square this Pac-Man character is currently on
	 * 
	 * @invar the square Pac-Man is on is not null
	 *  | CurrentSquare != null
	 * @invar the square Pac-Man is on is passable
	 *  | CurrentSquare.isPassable() == true
	 */
	private Square CurrentSquare;
	
	/**
	 * Returns the square this Pac-Man character is currently on
	 * @basic
	 */
	public Square getSquare() {
		return CurrentSquare; //geen rep exposure want Square is immutable
	}
	
	/**
	 * Returns the number of lives this Pac-Man character currently has
	 * @basic
	 */
	public int getNbLives() { 
		return lives;
	}

	/**
	 * Initializes a Pac-Man character with the given number of lives on the given square
	 * 
	 * @throws IllegalArgumentException if the given number of lives is less than 1
	 *  | 1 > nbLives
	 * @throws IllegalArgumentException if the given square is not passable
	 *  | square.isPassable() != true
	 * @post the number of lives equals the given number
	 *  | getNbLives() == nbLives
	 * @post the Pac-Man character's square equals the given square
	 *  | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if (1 > nbLives)
			throw new IllegalArgumentException("Lives must be at least 1");
		if (square.isPassable() != true)
			throw new IllegalArgumentException("Given square is not passable");
		lives = nbLives;
		CurrentSquare = square; //Squares zijn immutable
	}
	
	/**
	 * Sets this Pac-Man character's square to the given square
	 * 
	 * @mutates | this
	 * @throws IllegalArgumentException if argument {@code square} is null
	 *  | square == null
	 * @throws IllegalArgumentException if the given square is not passable
	 *  | square.isPassable() != true
	 * @post this Pac-Man character's square is equal to the given square
	 *  | getSquare() == square
	 * @post this Pac-Man character's number of lives remains unchanged
	 *  | getNbLives() == old(getNbLives())
	 */
	// geen @inspects want het argument square is immutable
	public void setSquare(Square square) { 
		if (square == null)
			throw new IllegalArgumentException("The given square is null");
		if (square.isPassable() != true) {
			throw new IllegalArgumentException("This square is not passable");
		}
		else
			CurrentSquare = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @mutates | this
	 * @throws IllegalStateException if the number of lives is less than 1 when the method is called
	 *  | getNbLives() < 1
	 * @post the lives are 1 less than before the method was called
	 *  | getNbLives() == old(getNbLives()) - 1
	 * @post the square this Pac-Man character is on remains unchanged
	 *  | getSquare() == old(getSquare())
	 */
	public void die() { 
		if (getNbLives() < 1)
			throw new IllegalStateException("Number of lives is already less than 1");
		lives--;
	}

}
