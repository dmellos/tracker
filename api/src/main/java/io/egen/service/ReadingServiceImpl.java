package io.egen.service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.BadRequestException;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.ReadingRepository;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepository readingRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    AlertService alertService;

    @Autowired
    EmailService emailService;



    @Transactional(readOnly = true)
    public List<Reading> findAllReadings() {
        List<Reading> existingReading = readingRepository.findAllReadings();
        if (existingReading == null) {
            throw new ResourceNotFoundException("No Readings found");
        }
        return existingReading;
    }

    @Transactional(readOnly = true)
    public List<Reading> findReadingByVIN(String readingVIN) {
        List<Reading> existingReading = readingRepository.findReadingByVIN(readingVIN);
        if (existingReading == null) {
            throw new ResourceNotFoundException("Reading with Vin: " + readingVIN + "not found");
        }
        return existingReading;
    }

    @Transactional
    public void insertAlert(List<Alert> alertList, Vehicle vehicle, Reading reading) {
        if (alertList == null) {
            return;
        }
        for (Alert alert : alertList) {
            if (alert != null) {
                alertRepository.createAlert(alert);
                emailService.sendEmail(vehicle, reading, alert);
            }
        }
    }

    public void checkAlerts(Vehicle vehicle, Reading reading) {
        List<Alert> alertList = new ArrayList<>();
        alertList.add(alertService.checkEngineRPM(vehicle, reading));
        alertList.add(alertService.checkEngineCoolant(vehicle, reading));
        alertList.add(alertService.checkEngineLightOn(vehicle, reading));
        alertList.add(alertService.checkFuelVolume(vehicle, reading));
        alertList.add(alertService.checkTirePressure(vehicle, reading));
        insertAlert(alertList, vehicle, reading);
    }

    @Transactional
    public void createReading(Reading reading) {
        if (reading == null) {
            throw new BadRequestException("The Reading is Empty");
        }
        String readingVIN = reading.getVin();
        Vehicle existingVehicle = vehicleRepository.findVehicleByVIN(readingVIN);
        if (existingVehicle != null) {
            checkAlerts(existingVehicle, reading);
        }
        readingRepository.createReading(reading);
    }


}
