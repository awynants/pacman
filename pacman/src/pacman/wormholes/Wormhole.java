package pacman.wormholes;

import logicalcollections.LogicalSet;

/**
 * Represents a wormhole in a wormhole graph, which connects a departure portal and an arrival portal
 * 
 * @invar The departure portal cannot be null
 * 		| getDeparturePortal() != null
 * @invar The arrival portal cannot be null
 * 		| getArrivalPortal() != null
 * @invar The departure portal-wormhole bidirectional association is consistent
 * 		| getDeparturePortal().getWormholes().contains(this)
 * @invar The arrival portal-wormhole bidirectional association is consistent
 * 		| getArrivalPortal().getWormholes().contains(this)
 */
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
		givenDeparturePortal.addWormhole(this);
		givenArrivalPortal.addWormhole(this);
	}
	
	
	/**
	 * Changes the current departure portal of this wormhole to the given departure portal
	 * 
	 * @throws IllegalArgumentException if the given departure portal is null
	 * 		| givenDeparturePortal == null
	 * 
	 * @mutates_properties | getDeparturePortal(), givenDeparturePortal.getWormholes(), getDeparturePortal().getWormholes()
	 * 
	 * @post This wormhole's departure portal is now the given departure portal
	 * 		| getDeparturePortal() == givenDeparturePortal
	 * @post The old departure portal's set of wormholes will equal its old set of wormholes minus this wormhole, unless the old and given departure portal are the same
	 * 		| old(getDeparturePortal()) == givenDeparturePortal || old(getDeparturePortal()).getWormholes().equals(LogicalSet.minus(old(getDeparturePortal().getWormholes()), this))
	 * @post the given departure portal's set of wormholes will equal its old set of wormholes plus this wormhole, unless the old and given departure portal are the same
	 * 		| old(getDeparturePortal()) == givenDeparturePortal || givenDeparturePortal.getWormholes().equals(LogicalSet.plus(old(givenDeparturePortal.getWormholes()), this))
	 */
	public void setDeparturePortal(DeparturePortal givenDeparturePortal) {
		if (givenDeparturePortal == null) {
			throw new IllegalArgumentException("The given departure portal cannot be null");
		}
		
		if (givenDeparturePortal != this.getDeparturePortal()) {
			DeparturePortal oldDeparturePortal = this.getDeparturePortal();
			oldDeparturePortal.removeWormhole(this);
			departurePortal = givenDeparturePortal;
			givenDeparturePortal.addWormhole(this);
		}
	}
	
	/**
	 * Changes the current departure portal of this wormhole to the given arrival portal
	 * 
	 * @throws IllegalArgumentException if the given departure portal is null
	 * 		| givenArrivalPortal == null
	 * 
	 * @mutates_properties | getDeparturePortal(), givenArrivalPortal.getWormholes(), getArrivalPortal().getWormholes()
	 * 
	 * @post This wormhole's arrival portal is now the given arrival portal
	 * 		| getArrivalPortal() == givenArrivalPortal
	 * @post The old arrival portal's set of wormholes will equal its old set of wormholes minus this wormhole, unless the old and given arrival portal are the same
	 * 		| old(getArrivalPortal()) == givenArrivalPortal || old(getArrivalPortal()).getWormholes().equals(LogicalSet.minus(old(getArrivalPortal().getWormholes()), this))
	 * @post the given arrival portal's set of wormholes will equal its old set of wormholes plus this wormhole, unless the old and given arrival portal are the same
	 * 		| old(getArrivalPortal()) == givenArrivalPortal || givenArrivalPortal.getWormholes().equals(LogicalSet.plus(old(givenArrivalPortal.getWormholes()), this))
	 */
	public void setArrivalPortal(ArrivalPortal givenArrivalPortal) {
		if (givenArrivalPortal == null) {
			throw new IllegalArgumentException("The given arrival portal cannot be null");
		}
		
		if (givenArrivalPortal != this.getArrivalPortal()) {
			ArrivalPortal oldArrivalPortal = this.getArrivalPortal();
			oldArrivalPortal.removeWormhole(this);
			arrivalPortal = givenArrivalPortal;
			givenArrivalPortal.addWormhole(this);
		}
	}
}
