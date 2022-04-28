package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	public Vehicle findByLicenseNumber(String licenseNumber);

	// findAll return Page in order to apply sorting
	public Page<Vehicle> findAll(Pageable pageable);
}
