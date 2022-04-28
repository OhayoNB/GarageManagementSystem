package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exception.CustomException;
import com.model.Vehicle;
import com.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	protected VehicleRepository vehicleRepository;

	public VehicleService() {

	}

	public void addVehicle(Vehicle vehicle) throws CustomException {
		// Check if the vehicle is already stored in the DB before adding it
		if(!(vehicleRepository.findByLicenseNumber(vehicle.getLicenseNumber()) == null)) {
			throw new CustomException("The vehicle you are trying to add is already exists");
		}
		// Invoke the setWheels function to attach the wheels to the vehicle
		vehicle.setWheels();
		vehicleRepository.save(vehicle);
	}

	public Vehicle getVehicleByLicenseNumber(String licenseNumber) throws CustomException {
		if(vehicleRepository.findByLicenseNumber(licenseNumber) == null) {
			throw new CustomException("No vehicle found with entered license number");
		}
		return vehicleRepository.findByLicenseNumber(licenseNumber);
	}

	public Page<Vehicle> getAllVehicles(Pageable pageable) throws CustomException {
		// Checking if there are any vehicles to get
		if((vehicleRepository.findAll()).isEmpty()) {
			throw new CustomException("The garage is empty of vehicles");
		}
		return vehicleRepository.findAll(pageable);
	}
	
	public void inflateVehicleTiresByLicenseNumber(String licenseNumber) throws CustomException {
		if(vehicleRepository.findByLicenseNumber(licenseNumber) == null) {
			throw new CustomException("Cannot inflate tires, no vehicle found with entered license number");
		}
		// Get the vehicle from the DB and store it in an object
		Vehicle v = vehicleRepository.findByLicenseNumber(licenseNumber);
		
		// Use the object getter to reach the wheels, loop through them and set the tire pressure of each one of them
		v.getWheels().forEach(w -> w.setTirePressure(v.getMaximumTirePressure()));
		vehicleRepository.save(v);
	}
	
	public void addEnergyByLicenseNumber(@PathVariable("licenseNumber")String licenseNumber, @PathVariable("energy")int energy) throws CustomException {
		if(energy <= 0) {
			throw new CustomException("Energy must be positive in order to add to vehicle");
		}
		if(vehicleRepository.findByLicenseNumber(licenseNumber) == null) {
			throw new CustomException("Cannot add energy, no vehicle found with entered license number");
		}
		Vehicle v = vehicleRepository.findByLicenseNumber(licenseNumber);
		v.setAvailableEnergy(energy);
		vehicleRepository.save(v);
	}
}
