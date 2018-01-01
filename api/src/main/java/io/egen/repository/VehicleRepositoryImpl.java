package io.egen.repository;

import io.egen.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vehicle> findAllVehicles() {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery("Vehicle.getAllVehicles", Vehicle.class);
        return query.getResultList();
    }

    @Override
    public Vehicle findVehicleByVIN(String VIN) {
        return entityManager.find(Vehicle.class, VIN);
    }

    @Override
    public void createVehicle(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return entityManager.merge(vehicle);
    }
}
