package io.egen.service;

import io.egen.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAllVehicles();

    Vehicle findVehicleByVIN(String vehicleVIN);

    void createVehicle(List<Vehicle> vehicleList);

    Vehicle updateVehicle(String vehicleVIN, Vehicle vehicle);

}
