package com.parking.web.services.Entity;

public class EntranceParking {
	String entranceName;
	int entranceRows;
	int entranceCols;
	public String getEntranceName() {
		return entranceName;
	}
	public void setEntranceName(String entranceName) {
		this.entranceName = entranceName;
	}
	public int getEntranceRows() {
		return entranceRows;
	}
	public void setEntranceRows(int entranceRows) {
		this.entranceRows = entranceRows;
	}
	public int getEntranceCols() {
		return entranceCols;
	}
	public void setEntranceCols(int entranceCols) {
		this.entranceCols = entranceCols;
	}
	
	public EntranceParking(String entranceName, int entranceRows, int entranceCols) {
		super();
		this.entranceName = entranceName;
		this.entranceRows = entranceRows;
		this.entranceCols = entranceCols;
	}
	
	
}
