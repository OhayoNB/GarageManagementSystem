package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wheel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	// JsonBackReference annotation to omit wheel from being serialized
	// ManyToOne annotation to reverse the relationship to the vehicle that the wheel is assigned to
	@JsonBackReference
	@ManyToOne
    private Vehicle vehicle;

    private Integer tirePressure;

	public void setId(Long id) {
		this.id = id;
	}

	public Wheel setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public Wheel setTirePressure(Integer tirePressure) {
		this.tirePressure = tirePressure;
		return this;
	}

    
}