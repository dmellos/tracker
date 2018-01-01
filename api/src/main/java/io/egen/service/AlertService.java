package io.egen.service;

import io.egen.entity.Alert;
import io.egen.entity.Vehicle;
import io.egen.entity.Reading;

public interface AlertService {

        Alert checkFuelVolume(Vehicle vehicle, Reading reading);

        Alert checkEngineLightOn(Vehicle vehicle, Reading reading);

        Alert checkEngineCoolant(Vehicle vehicle, Reading reading);

        Alert checkEngineRPM(Vehicle vehicle, Reading reading);

        Alert checkTirePressure(Vehicle vehicle, Reading reading);

    }

