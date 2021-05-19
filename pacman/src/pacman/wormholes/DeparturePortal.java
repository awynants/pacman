package pacman.wormholes;

import java.util.Set;
import pacman.Square;

/**
 * Represents a departure portal in a wormhole graph.
 * 
 * @invar The portal has to be on an existing square
 * 		| getSquare() != null
 * @invar | getWormholes() != null
 * @invar The value null will never be stored as a wormhole of this portal
 * 		| getWormholes().stream().allMatch(w -> w != null)
 * @invar The portal-wormhole association is consistent
 * 		| getWormholes().stream().allMatch(w -> w.getDeparturePortal() == this)
 */
public class DeparturePortal {

	/**
	 * @representationObject
	 * This variable represents the square of this departure portal
	 * 
	 * @invar The square of this portal is not null
	 * 		| squareOfPortal != null
	 */
	private Square squareOfPortal;
	
	/**
	 * @representationObject
	 * This variable is a set containing all the wormholes that this departure
	 * portal is connected to
	 * 
	 * @invar The set cannot be null
	 * 		|connectedWormholes != null
	 * @invar The set cannot contain a null value
	 * 		|connectedWormholes.stream().allMatch(w -> w != null)
	 */
	private Set<Wormhole> connectedWormholes;
	
	/**
	 * Returns the square that the departure portal is on
	 * 
	 * @post the result is not null
	 * 		|result != null
	 * @basic
	 */
	public Square getSquare() {
		return squareOfPortal;
	}
	
	/**
	 * Returns the wormholes connected to this departure portal
	 * 
	 * @creates | this
	 * 
	 * @invar | getWormholesInternal().stream().allMatch(w -> w.getDeparturePortalInternal() == this)
	 * 
	 * @post |result != null
	 * @post |result.stream().allMatch(s -> s != null)
	 * 
	 * @peerObjects (package-level)
	 */
	Set<Wormhole> getWormholesInternal() {
		return Set.copyOf(connectedWormholes);
	}
	
	/**
	 * Returns the wormholes connected to this departure portal
	 * 
	 * @creates | this
	 * 
	 * @post |result != null
	 * @post |result.stream().allMatch(w -> w != null)
	 * 
	 * @peerObjects (package-level)
	 */
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(connectedWormholes);
	}
	
	/**
	 * Creates an object that represents a departure portal on the given square
	 * that is not yet connected to a wormhole
	 * 
	 * @mutates | this
	 * 
	 * @invar The given square cannot be null
	 * 		| square != null
	 * 
	 * @post | square == getSquare()
	 * @post | getWormholes().isEmpty()
	 */
	public DeparturePortal(Square square){
		if (square == null) {
			throw new IllegalArgumentException("The square of the departure portal can't be null");	
		}
		squareOfPortal = square;
	}
	
	
}
