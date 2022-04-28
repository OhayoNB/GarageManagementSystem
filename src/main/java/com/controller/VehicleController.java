package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomException;
import com.model.Vehicle;
import com.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	// Add new vehicle to the DB by using Post method
	@PostMapping("/addVehicle")
	public void addVehicle(@RequestBody Vehicle vehicle) throws CustomException {
		vehicleService.addVehicle(vehicle);
	}

	// Get vehicle details by license number using Get method
	@GetMapping("/getVehicleByLicenseNumber/{licenseNumber}")
	public Vehicle getVehicleByLicenseNumber(@PathVariable("licenseNumber") String licenseNumber) throws CustomException {
		return vehicleService.getVehicleByLicenseNumber(licenseNumber);
	}

	// Get all available vehicles 
	@GetMapping("/getAllVehicles")
	public ResponseEntity<List<Vehicle>> getAllVehicles(Pageable pageable) throws CustomException {
		return ResponseEntity.ok(vehicleService.getAllVehicles(pageable).getContent());
	}
	
	// Set tire pressure to maximum by updating the wheels that attached to the vehicle using Put method
	@PutMapping("/inflateVehicleTiresByLicenseNumber/{licenseNumber}")
	public void inflateVehicleTiresByLicenseNumber(@PathVariable("licenseNumber")String licenseNumber) throws CustomException {
		vehicleService.inflateVehicleTiresByLicenseNumber(licenseNumber);
	}
	
	// Add energy to vehicle by license number using Put method
	@PutMapping("/addEnergyByLicenseNumber/{licenseNumber}/{energy}")
	public void addEnergyByLicenseNumber(@PathVariable("licenseNumber")String licenseNumber, @PathVariable("energy")int energy) throws CustomException {
		vehicleService.addEnergyByLicenseNumber(licenseNumber, energy);
	}
}
