package io.egen.repository;

import io.egen.entity.Reading;

import java.util.List;

public interface ReadingRepository {
    List<Reading> findAllReadings();

    List<Reading> findReadingByVIN(String vehicleVIN);

    void createReading(Reading reading);


}
