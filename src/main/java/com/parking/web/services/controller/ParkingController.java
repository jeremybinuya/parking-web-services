package com.parking.web.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.web.services.Entity.SlotParking;
import com.parking.web.services.Entity.Parking;
import com.parking.web.services.Entity.ParkingParameter;
import com.parking.web.services.service.ParkingService;

@RestController
@RequestMapping("/parking/v1")
public class ParkingController {
	
	@Autowired
	ParkingService parkingService;
	
	
	@GetMapping(path="/")
	public String getSlot() {
		return "Parking Lot";
	}
	
	@GetMapping(path="/parking-slot")
	public List<Parking> getAllParkingSlots () {
		return parkingService.getAllParkingSlots();
	}
	
	@PostMapping(path = "/park-car")
	public String parkCar(@RequestBody ParkingParameter parameter) {
		return parkingService.parkCar(parameter.getSize(), parameter.getEntrance());
	}
	
	@PostMapping(path = "/unpark-car")
	public String unparkCar(@RequestBody ParkingParameter parameter) {
		return parkingService.unParkCar(parameter.getRow(), parameter.getCol());
	}

}
