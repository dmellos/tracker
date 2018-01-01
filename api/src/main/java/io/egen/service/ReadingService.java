package io.egen.service;

import io.egen.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAllReadings();

    List<Reading> findReadingByVIN(String readingVIN);

    void createReading(Reading reading);



}
