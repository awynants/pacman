package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;
import pacman.Square;
import logicalcollections.LogicalSet;

/**
 * Represents an arrival portal in a wormhole graph.
 * 
 * @invar The portal has to be on an existing square
 * 		| getSquare() != null
 * @invar | getWormholes() != null
 * @invar The value null will never be stored as a wormhole of this portal
 * 		| getWormholes().stream().allMatch(w -> w != null)
 * @invar The portal-wormhole association is consistent
 * 		| getWormholes().stream().allMatch(w -> w.getArrivalPortal() == this)
 */
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
	
	/**
	 * Creates an object that represents an arrival portal on the given square that is not yet connected to a wormhole
	 * 
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException if the given square is null
	 * 		| square == null
	 * 
	 * @post | square == getSquare()
	 * @post | getWormholes().isEmpty()
	 */
	public ArrivalPortal(Square square){
		if (square == null) {
			throw new IllegalArgumentException("The square of the arrival portal can't be null");	
		}
		squareOfPortal = square;
	}
	
	
	/**
	 * Adds the given wormhole to the set of wormholes
	 * 
	 * @throws IllegalArgumentException if the given wormhole is null
	 * 		| wormhole == null
	 * 
	 * @mutates | this
	 * 
	 * @post the new set of wormholes equals the old set of wormholes plus the given wormhole
	 * 		| getWormholesInternal().equals(LogicalSet.plus(old(getWormholesInternal()), wormhole))
	 */
	void addWormhole(Wormhole wormhole) {
		if (wormhole == null) {
			throw new IllegalArgumentException("The given wormhole cannot be null");
		}
		connectedWormholes.add(wormhole);
	}
	
	/**
	 * Removes the given wormhole from the set of wormholes
	 * 
	 * @throws IllegalArgumentException if the given wormhole is null
	 * 		| wormhole == null
	 * 
	 * @mutates | this
	 * 
	 * @post the new set of wormholes equals the old set of wormholes minus the given wormhole
	 * 		| getWormholesInternal().equals(LogicalSet.minus(old(getWormholesInternal()), wormhole))
	 */
	void removeWormhole(Wormhole wormhole) {
		if (wormhole == null) {
			throw new IllegalArgumentException("The given wormhole cannot be null");
		}
		connectedWormholes.remove(wormhole);
	}
}
