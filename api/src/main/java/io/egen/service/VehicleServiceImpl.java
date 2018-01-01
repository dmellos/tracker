package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.exception.BadRequestException;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAllVehicles();
        if (vehicleList == null) {
            throw new ResourceNotFoundException("There are no vehicles in the database");
        }
        return vehicleList;
    }

    @Transactional(readOnly = true)
    public Vehicle findVehicleByVIN(String vehicleVIN) {
        Vehicle existingVehicle = vehicleRepository.findVehicleByVIN(vehicleVIN);
        if (existingVehicle == null) {
            throw new ResourceNotFoundException("Vehicle with Vin: " + vehicleVIN + " doesn't exist");
        }
        return existingVehicle;
    }

    @Transactional
    public void createVehicle(List<Vehicle> vehicleList) {
        if (vehicleList.isEmpty() || vehicleList == null) {
            throw new BadRequestException("Vehicle list is Empty");
        }
        for (Vehicle vehicle : vehicleList) {
            Vehicle existingVehicle = vehicleRepository.findVehicleByVIN(vehicle.getVin());
            if (existingVehicle == null) {
                vehicleRepository.createVehicle(vehicle);
            } else {
                vehicleRepository.update(vehicle);
            }
        }

    }

    @Transactional
    public Vehicle updateVehicle(String vehicleVIN, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findVehicleByVIN(vehicleVIN);
        if (existingVehicle == null) {
            throw new ResourceNotFoundException("Vehicle with " + vehicleVIN + " doesn't exist");
        }
        return vehicleRepository.update(vehicle);
    }
}
