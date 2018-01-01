package io.egen.controller;

import io.egen.entity.Vehicle;
import io.egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RequestMapping(value = "vehicles")
@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Vehicle> findAllVehicles(){
    return vehicleService.findAllVehicles();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Vehicle findVehicleByVIN(@PathVariable("id") String VIN){
        return vehicleService.findVehicleByVIN(VIN);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createVehicle( @RequestBody List<Vehicle> vehicleList){
        vehicleService.createVehicle(vehicleList);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Vehicle update(@PathVariable("id") String VIN, Vehicle vehicle){
        return vehicleService.updateVehicle(VIN, vehicle);
    }
}
