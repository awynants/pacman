package pacman.wormholes;

import logicalcollections.LogicalSet;

public class Wormhole {
	
	/**
	 * This field is a pointer for the wormhole's peer object of class DeparturePortal
	 * 
	 * @invar The wormhole always has a departure portal
	 * 		| departurePortal != null
	 */
	private DeparturePortal departurePortal;
	
	/**
	 * This field is a pointer for the wormhole's peer object of class ArrivalPortal
	 * 
	 * @invar The wormhole always has a arrival portal
	 * 		| arrivalPortal != null
	 */
	private ArrivalPortal arrivalPortal;
	
	
	/**
	 * Returns the departure portal of this wormhole
	 * 
	 * @invar | getDeparturePortalInternal().getWormholesInternal().contains(this)
	 * 
	 * @peerObject (package-level)
	 */
	DeparturePortal getDeparturePortalInternal() {
		return departurePortal;
	}
	
	/**
	 * Returns the departure portal of this wormhole
	 * @peerObject (package-level)
	 */
	public DeparturePortal getDeparturePortal() {
		return departurePortal;
	}
	
	/**
	 * Returns the arrival portal of this wormhole
	 * 
	 * @invar | getArrivalPortalInternal().getWormholesInternal().contains(this)
	 * 
	 * @peerObject (package-level)
	 */
	ArrivalPortal getArrivalPortalInternal() {
		return arrivalPortal;
	}
	
	/**
	 * Returns the departure portal of this wormhole
	 * @peerObject (package-level)
	 */
	public ArrivalPortal getArrivalPortal() {
		return arrivalPortal;
	}
	
	
	/**
	 * Creates an object that represents a wormhole that connects to an arrival portal and a departure portal
	 * 
	 * @mutates | this, givenDeparturePortal, givenArrivalPortal
	 * 
	 * @throws IllegalArgumentException if the given departure portal is null
	 * 		| givenDeparturePortal == null
	 * @throws IllegalArgumentException if the given arrival portal is null
	 * 		| givenArrivalPortal == null
	 * 
	 * @post This wormhole's departure portal will be the given departure portal
	 * 		| getDeparturePortal() == givenDeparturePortal
	 * @post This wormhole's arrival portal will be the given arrival portal
	 * 		| getArrivalPortal() == givenArrivalPortal
	 * @post The given departure portal's set of wormholes will be its old set of wormholes plus this one
	 * 		| givenDeparturePortal.getWormholes().equals(LogicalSet.plus(old(givenDeparturePortal.getWormholes()), this))
	 * @post The given arrival portal's set of wormholes will be its old set of wormholes plus this one
	 * 		| givenArrivalPortal.getWormholes().equals(LogicalSet.plus(old(givenArrivalPortal.getWormholes()), this))
	 */
	public Wormhole(DeparturePortal givenDeparturePortal, ArrivalPortal givenArrivalPortal) {
		if (givenDeparturePortal == null) {
			throw new IllegalArgumentException("The departure portal cannot be null");
		}
		if (givenArrivalPortal == null) {
			throw new IllegalArgumentException("The arrival portal cannot be null");
		}
		
		departurePortal = givenDeparturePortal;
		arrivalPortal = givenArrivalPortal;
		//givenDeparturePortal.getWormholesInternal().add(this);
		//givenArrivalPortal.getWormholesInternal().add(this);
		// DIT GROEN HIERBOVEN IS FOUT! Moet het vervangen met andere code morgen eens dat ik de mutatoren heb geschreven voor al deze klassen!
	}
}
