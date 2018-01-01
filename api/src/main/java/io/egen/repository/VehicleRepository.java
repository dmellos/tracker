package io.egen.repository;

import io.egen.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

    List<Vehicle> findAllVehicles();

    Vehicle findVehicleByVIN(String VIN);

    void createVehicle(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);
}
