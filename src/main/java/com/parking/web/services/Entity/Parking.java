package com.parking.web.services.Entity;

import java.time.LocalDateTime;

public class Parking {
	
	Boolean isOccupied;
	String parkDescription;
	String vehicleSize;
	LocalDateTime startParking;
	LocalDateTime endParking;
	Integer parkingRow;
	Integer parkingColumn;
	
	public Parking( Boolean isOccupied, String parkDescription, String vehicleSize,
			LocalDateTime startParking ,Integer parkingRow, Integer parkingColumn) {
		super();
		this.isOccupied = isOccupied;
		this.parkDescription = parkDescription;
		this.vehicleSize = vehicleSize;
		this.startParking = startParking;
		this.parkingRow = parkingRow;
		this.parkingColumn = parkingColumn;
	}
	
	public Boolean getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public String getVehicleSize() {
		return vehicleSize;
	}
	public void setVehicleSize(String vehicleSize) {
		this.vehicleSize = vehicleSize;
	}
	public LocalDateTime getStartParking() {
		return startParking;
	}
	public void setStartParking(LocalDateTime startParking) {
		this.startParking = startParking;
	}
	public LocalDateTime getEndParking() {
		return endParking;
	}
	public void setEndParking(LocalDateTime endParking) {
		this.endParking = endParking;
	}
	public Integer getParkingRow() {
		return parkingRow;
	}
	public void setParkingRow(Integer parkingRow) {
		this.parkingRow = parkingRow;
	}
	public Integer getParkingColumn() {
		return parkingColumn;
	}
	public void setParkingColumn(Integer parkingColumn) {
		this.parkingColumn = parkingColumn;
	}
	
	
}
