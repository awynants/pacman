package pacman.wormholes;

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
}
