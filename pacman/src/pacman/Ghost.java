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
	
	
	
	public Ghost(Square square, Direction direction) { throw new RuntimeException("Not yet implemented"); }
	
	public void setSquare(Square square) { throw new RuntimeException("Not yet implemented"); }
	
	public void setDirection(Direction direction) { throw new RuntimeException("Not yet implemented"); }
	
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
