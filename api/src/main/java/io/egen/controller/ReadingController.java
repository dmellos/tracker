package io.egen.controller;

import io.egen.entity.Reading;
import io.egen.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.List;


//@CrossOrigin(origins = "http://mocker.egen.io", maxAge = 3600)
@RequestMapping(value = "readings")
@RestController
public class ReadingController {

    @Autowired
    ReadingService readingService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> findAllReadings(){
        return readingService.findAllReadings();
    }

    @RequestMapping(method = RequestMethod.GET , value = "{/id}")
    public List<Reading> findReadingsByVIN(@PathVariable("id") String readingVIN){
        return readingService.findReadingByVIN(readingVIN);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createReading(@RequestBody Reading reading){
        readingService.createReading(reading);
    }


}
