package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 */
public class Ghost {
	
	/**
	 * @representationObject
	 * This variable represents the square the ghost is on.
	 * @invar The square of the ghost is not null.
	 * 	|squareOfGhost != null
	 * @invar The square of the ghost has to be passable.
	 * 	|squareOfGhost.isPassable()
	 */
	private Square squareOfGhost;
	/**
	 * @representationObject
	 * This variable represents the direction the ghost is going towards.
	 * @invar The direction of the ghost is not null.
	 * 	|directionOfGhost != null
	 */
	private Direction directionOfGhost;
	
	
	/**
	 * Returns a clone of the square that the ghost is on.
	 * @basic
	 */
	public Square getSquare() { return squareOfGhost; }
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 */
	public Direction getDirection() { return directionOfGhost; }
	
	
	/**
	 * Initializes a Ghost character on the given square and facing the given direction.
	 * @throws The given square cannot be null.
	 * 	| square == null
	 * @throws The given square has to be passable.
	 * 	| !(square.isPassable())
	 * @throws The given direction cannot be null.
	 * 	| direction == null
	 * @post The ghost's direction is equal to the given direction.
	 * 	| getDirection() == direction
	 * @post The ghost's square is equal to the given square.
	 * 	| getSquare() == square
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null) {
			throw new IllegalArgumentException("The given square is null.");
		}
		if (!(square.isPassable())){
			throw new IllegalArgumentException("The given square is not passable.");
		}
		if (direction == null) {
			throw new IllegalArgumentException("The given direction is null.");
		}
		
		squareOfGhost = square; //squares are immutable
		directionOfGhost = direction;
	}
	
	
	/**
	 * Sets this ghost object's square to the given square
	 * 
	 * @mutates | this
	 * @throws The given square cannot be null.
	 * 	| square == null
	 * @throws The given square has to be passable.
	 * 	| !(square.isPassable())
	 * @post This ghost's square is now the given square.
	 * 	| getSquare() == square
	 * @post This ghost's direction remains unchanged.
	 * 	| getDirection() == old(getDirection())
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("The given square is null.");
		}
		if (!(square.isPassable())){
			throw new IllegalArgumentException("The given square is not passable.");
		}
		squareOfGhost = square;
	}
	
	/**
	 * Sets this ghost object's direction to the given direction
	 * 
	 * @mutates | this
	 * @throws The given direction cannot be null.
	 * 	| direction == null
	 * @post The ghost's direction is equal to the given direction.
	 * 	| getDirection() == direction
	 * @post The ghost's square remains unchanged.
	 * 	| getSquare() == old(getSquare())
	 */
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("The given direction is null.");
		}
		directionOfGhost = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
