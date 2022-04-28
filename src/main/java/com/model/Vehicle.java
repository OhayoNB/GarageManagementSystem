package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vehicle {

	// Vehicle attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String modelName;

	private String licenseNumber;

	private Integer availableEnergy;

	private Integer maximumTirePressure;

	private VehicleType vehicleType;

	private EnergyType energyType;

	// OneToMany annotation to specify that every vehicle is attached to collection of wheels
	// JsonManagedReference annotation to get serialized normally
	@JsonManagedReference
	@OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL)
	private List<Wheel> wheels = new ArrayList<Wheel>();

	// Modify setter for wheels to set correct amount of wheels for vehicle type
	public void setWheels() {

		switch (this.vehicleType) {
		case CAR:
			for (int i = 0; i < 4; i++) {
				// Add the wheels and attach the vehicle id and tire pressure for the wheel
				this.wheels.add(new Wheel().setVehicle(this).setTirePressure(this.maximumTirePressure));
			}
			break;

		case MOTORCYCLE:
			for (int i = 0; i < 2; i++) {
				this.wheels.add(new Wheel().setVehicle(this).setTirePressure(this.maximumTirePressure));
			}
			break;

		case TRUCK:
			for (int i = 0; i < 16; i++) {
				this.wheels.add(new Wheel().setVehicle(this).setTirePressure(this.maximumTirePressure));
			}
			break;
		}	
	}
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public void setAvailableEnergy(Integer availableEnergy) {
		this.availableEnergy = availableEnergy;
	}

	public void setMaximumTirePressure(Integer maximumTirePressure) {
		this.maximumTirePressure = maximumTirePressure;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setEnergyType(EnergyType energyType) {
		this.energyType = energyType;
	}
}
