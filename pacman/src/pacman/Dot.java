package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * @immutable
 */
public class Dot {
	
	/**
	 * @representationObject
	 * this variable represents the square that this dot is on
	 */
	private Square squareOfDot;
	
	/**
	 * Returns the square that this dot is on.
	 */
	public Square getSquare() { 
		return squareOfDot; 
		}
	
	/**
	 * Initializes a dot at the given square.
	 * @post this dot's square equals the given square
	 *   | getSquare() == square
	 */
	public Dot(Square square) {
		squareOfDot = square; 
		}

}
