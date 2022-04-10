package com.parking.web.services.Entity;

import java.util.List;

public class SlotParking {
	String slot;
	List<Parking> parkingDescription;
	
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public List<Parking> getParkingDescription() {
		return parkingDescription;
	}

	public void setParkingDescription(List<Parking> parkingDescription) {
		this.parkingDescription = parkingDescription;
	}

	public SlotParking(String slot, List<Parking> parkingDescription) {
		super();
		this.slot = slot;
		this.parkingDescription = parkingDescription;
	}
	
	
	
	
}
