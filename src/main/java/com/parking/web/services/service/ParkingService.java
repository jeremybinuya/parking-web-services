package com.parking.web.services.service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.parking.web.services.Entity.SlotParking;
import com.parking.web.services.Entity.EntranceParking;
import com.parking.web.services.Entity.Parking;

@Service
public class ParkingService {

	private static List<Parking> parkingSlots = new ArrayList<Parking>();
//	private static List<Parking> parkingSlots2 = new ArrayList<Parking>();
//	private static List<Parking> parkingSlots3 = new ArrayList<Parking>();
//	private static List<SlotParking> entranceParking = new ArrayList<SlotParking>();

	private static List<EntranceParking> entryPoint = new ArrayList<EntranceParking>();

	private static int maxCols = 8;
	private static int maxRows = 5;

	// TODO: Harcoded Parking Lot spaces
	static {

		String[] parkingSize = { "SP", "MP", "LP" };
		Random r = new Random();
		boolean isZero;
		// TODO: with random generation of parking spaces
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				if (j == 0 || i == 0 || i == maxRows - 1 || j == maxCols - 1) {
					isZero = true;
				} else {
					isZero = false;
				}

				if (!isZero) {
					int randomNumber = r.nextInt(parkingSize.length);
					parkingSlots.add(new Parking(false, parkingSize[randomNumber], null, null, i, j));
				}
			}
		}

		// TODO: entry point
		entryPoint.add(new EntranceParking("A", 0, 1));
		entryPoint.add(new EntranceParking("B", 0, 4));
		entryPoint.add(new EntranceParking("C", maxRows, 1));

		// TODO: without random generation of parking spaces
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 1));
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 2));
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 3));
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 4));
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 5));
//		parkingSlots1.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 6));
//		
//		parkingSlots2.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 1));
//		parkingSlots2.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 2));
//		parkingSlots2.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 3));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 4));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 5));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 6));
//		
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 1));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 2));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 3));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 4));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 5));
//		parkingSlots3.add(new Parking( false, parkingSize[randomNumber], null, null, null, 1, 6));
//		
		// entranceParking.add(new SlotParking("Parking Slot" ,parkingSlots1));
//		entranceParking.add(new SlotParking("Parking Slot 2" ,parkingSlots3));
//		entranceParking.add(new SlotParking("Parking Slot 3" ,parkingSlots3));
	}

	// TOOD: get all parking slots
	public List<Parking> getAllParkingSlots() {
		return parkingSlots;
	}

	public String parkCar(int size, String entrance) {
		int parkingSizeValue = 0;
		int newRow = -1, newCol = -1, distance = 9999;
		String parkdescription = "";
		boolean isZero;
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				if (j == 0 || i == 0 || i == maxRows - 1 || j == maxCols - 1) {
					isZero = true;
				} else {
					isZero = false;
				}

				if (!isZero) {
					if (findByParking(i, j).getParkDescription() == "MP") {
						parkingSizeValue = 1;
					} else if (findByParking(i, j).getParkDescription() == "LP") {
						parkingSizeValue = 2;
					} else {
						parkingSizeValue = 0;
					}

					if (size <= parkingSizeValue) {
						int computeDistance = Math
								.abs(findByEntrance(entrance).getEntranceRows() - findByParking(i, j).getParkingRow()
										+ Math.abs(findByEntrance(entrance).getEntranceCols()
												- findByParking(i, j).getParkingColumn()));

						if (distance > computeDistance && !findByParking(i, j).getIsOccupied()) {
							distance = computeDistance;
							newRow = i;
							newCol = j;
							parkdescription = findByParking(i, j).getParkDescription();
							System.out.println(newRow + " " + newCol + " " + " " + parkdescription);
						}
					}
				}
			}
		}
		System.out.println("================================================");
		System.out.println(newRow + " " + newCol + " " + " " + parkdescription);
		if (newRow == -1 && newCol == -1) {
			return "No parking slot found";
		} else {
			parkingSlots.remove(findByParking(newRow, newCol));
			parkingSlots.add(new Parking(true, parkdescription, size == 0 ? "S" : size == 1 ? "M" : "L",
					LocalDateTime.now(), newRow, newCol));
		}

		return newRow + " " + newCol;
	}

	public Parking findByParking(int row, int col) {
		for (Parking parking : parkingSlots) {
			if (parking.getParkingRow() == row && parking.getParkingColumn() == col) {
				return parking;
			}
		}
		return null;
	}

	public String unParkCar(int row, int col) {
		String parkdescription = findByParking(row, col).getParkDescription();
		LocalDateTime startParking = findByParking(row, col).getStartParking();
		LocalDateTime now = LocalDateTime.now();

		long diffTime = ChronoUnit.MILLIS.between(startParking, now);

		System.out.println(diffTime);
		long remainingTime = diffTime;
		long t24 = 1000 * 60 * 24;
		long t1h = 1000 * 60 * 24;

		double charges = 0;

		double hourlyCharge = 0;

		if (parkdescription == "SP") {
			hourlyCharge = 20;
		} else if (parkdescription == "MP") {
			hourlyCharge = 60;
		} else if (parkdescription == "LP") {
			hourlyCharge = 100;
		}

		if (remainingTime > t24) {
			long n24 = (remainingTime / t24);
			charges += n24 * 5000;
			diffTime -= (n24 * t24);
		}

		if (remainingTime > (t1h * 3)) {
			remainingTime -= (t1h * 3);
			charges += 40;
		}

		if (remainingTime > 0) {
			long remainingHours = (long) Math.ceil(remainingTime / t1h);
			charges += remainingHours * hourlyCharge;
		}

		parkingSlots.remove(findByParking(row, col));
		parkingSlots.add(new Parking(false, parkdescription, null, null, row, col));
		return "Total charges " + charges;
	}

	public EntranceParking findByEntrance(String entrance) {
		for (EntranceParking entryParking : entryPoint) {
			if (entryParking.getEntranceName().equals(entrance)) {
				return entryParking;
			}
		}

		return null;
	}

}
