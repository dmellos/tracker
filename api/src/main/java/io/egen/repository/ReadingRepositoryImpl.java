package io.egen.repository;

import io.egen.entity.Reading;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Reading> findAllReadings() {
        TypedQuery<Reading> readingsQuery = entityManager.createNamedQuery("Reading.findAllReadings", Reading.class);
        return readingsQuery.getResultList();
    }

    @Override
    public List<Reading> findReadingByVIN(String vehicleVIN) {
        TypedQuery<Reading> readingQuery = entityManager.createNamedQuery("Reading.findReadingsByVIN", Reading.class);
        readingQuery.setParameter("paramVIN", vehicleVIN);
        return readingQuery.getResultList();
    }

    @Override
    public void createReading(Reading reading) {
        entityManager.persist(reading);
    }


}
