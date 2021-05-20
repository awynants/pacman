package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;
import pacman.Square;

public class ArrivalPortal {
	
	/**
	 * @representationObject
	 * This variable represents the square of this arrival portal
	 * 
	 * @invar The square of this portal is not null
	 * 		| squareOfPortal != null
	 */
	private Square squareOfPortal;
	
	/**
	 * @representationObject
	 * This variable is a set containing all the wormholes that this arrival
	 * portal is connected to
	 * 
	 * @invar The set cannot be null
	 * 		|connectedWormholes != null
	 * @invar The set cannot contain a null value
	 * 		|connectedWormholes.stream().allMatch(s -> s != null)
	 */
	private Set<Wormhole> connectedWormholes = new HashSet<Wormhole>();
	
	
	/**
	 * Returns the square that the arrival portal is on
	 * 
	 * @post the result is not null
	 * 		|result != null
	 * @basic
	 */
	public Square getSquare() {
		return squareOfPortal;
	}
	
	/**
	 * Returns the wormholes connected to this arrival portal
	 * 
	 * @creates | this
	 * 
	 * @invar | getWormholesInternal().stream().allMatch(w -> w.getArrivalPortalInternal() == this)
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
	 * Returns the wormhole connected to this arrival portal
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
}
