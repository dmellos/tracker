package io.egen.service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {
    @Override
    public Alert checkFuelVolume(Vehicle vehicle, Reading reading) {
        Alert alert = null;
        double minFuelVolumeValue = (0.1) * vehicle.getMaxFuelVolume();
        if (reading.getFuelVolume() < minFuelVolumeValue) {
            alert = new Alert();
            alert.setReading(reading);
            alert.setPriority(Alert.Priority.MEDIUM);
            alert.setAlertType(Alert.AlertType.FUELVOLUME);
            alert.setVehicle(vehicle);
        }
        return alert;
    }

    @Override
    public Alert checkEngineLightOn(Vehicle vehicle, Reading reading) {
        Alert alert = null;
        if (reading.isCheckEngineLightOn()) {
            alert = new Alert();
            alert.setReading(reading);
            alert.setPriority(Alert.Priority.LOW);
            alert.setAlertType(Alert.AlertType.CHECKENGINELIGHTON);
            alert.setVehicle(vehicle);
        }
        return alert;
    }

    @Override
    public Alert checkEngineCoolant(Vehicle vehicle, Reading reading) {
        Alert alert = null;
        if (reading.isEngineCoolantLow()) {
            alert = new Alert();
            alert.setReading(reading);
            alert.setPriority(Alert.Priority.LOW);
            alert.setAlertType(Alert.AlertType.ENGINECOOLANTLOW);
            alert.setVehicle(vehicle);
        }
        return alert;
    }

    @Override
    public Alert checkEngineRPM(Vehicle vehicle, Reading reading) {
        Alert alert = null;
        if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {
            alert = new Alert();
            alert.setReading(reading);
            alert.setPriority(Alert.Priority.HIGH);
            alert.setAlertType(Alert.AlertType.ENGINERPM);
            alert.setVehicle(vehicle);
        }
        return alert;
    }

    @Override
    public Alert checkTirePressure(Vehicle vehicle, Reading reading) {
        Alert alert = null;
        List<Double> tires = new ArrayList<>();
        tires.add(reading.getTires().getFrontLeft());
        tires.add(reading.getTires().getFrontRight());
        tires.add(reading.getTires().getRearLeft());
        tires.add(reading.getTires().getRearRight());

        for (Double tire : tires) {
            if (tire < 32 || tire > 36) {
                alert = new Alert();
                alert.setReading(reading);
                alert.setPriority(Alert.Priority.LOW);
                alert.setAlertType(Alert.AlertType.TIRES);
                alert.setVehicle(vehicle);
                break;
            }
        }
        return alert;
    }
}
