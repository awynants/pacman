package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * @invar the number of lives is at least 0
 *   | 0 <= getNbLives()
 */
public class PacMan {
	
	/**
	 * @representationObject
	 * This variable represents the number of lives of this Pac-Man character
	 * @invar | 0 <= lives
	 */
	private int lives;
	
	/**
	 * @representationObject
	 * This variable represents the square this Pac-Man character is currently on
	 * @invar CurrentSquare != null
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
	 * @throws IllegalArgumentException if the given number of lives is less than 1
	 *  | 1 > nbLives
	 * @post the number of lives equals the given number
	 *  | getNbLives() == nbLives
	 * @post the Pac-Man character's square equals the given square
	 *  | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if (1 > nbLives)
			throw new IllegalArgumentException("Lives must be at least 1");
		lives = nbLives;
		CurrentSquare = square; //Squares zijn immutable
	}
	
	/**
	 * Sets this Pac-Man character's square to the given square
	 * @throws IllegalArgumentException if the given square is not a neighbor of the current square
	 *  | getSquare().getNeighbor(Direction.LEFT) != square && 
	 *  | getSquare().getNeighbor(Direction.UP) != square &&
	 *  | getSquare().getNeighbor(Direction.DOWN) != square && 
	 *  | getSquare().getNeighbor(Direction.RIGHT) != square
	 * @throws IllegalArgumentException if the given square is not passable
	 *  | square.isPassable() != true
	 * @post this Pac-Man character's square is equal to the given square
	 *  | getSquare() == square
	 * @post this Pac-Man character's number of lives remains unchanged
	 *  | getNbLives() == old(getNbLives())
	 */
	public void setSquare(Square square) { 
		if (getSquare().getNeighbor(Direction.LEFT) != square && 
			getSquare().getNeighbor(Direction.UP) != square &&
			getSquare().getNeighbor(Direction.DOWN) != square && 
			getSquare().getNeighbor(Direction.RIGHT) != square) {
			throw new IllegalArgumentException("This square is not a neighbor of the current square");
		}
		if (square.isPassable() != true) {
			throw new IllegalArgumentException("This square is not passable");
		}
		CurrentSquare = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
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
