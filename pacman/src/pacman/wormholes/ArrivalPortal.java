package pacman.wormholes;

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
	private Set<Wormhole> connectedWormholes;
}
