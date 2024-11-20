package org.vehicle.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.vehicle.service.model.VehicleServiceModel;

import java.util.List;

@Repository
public interface VehicleServiceRepository extends MongoRepository<VehicleServiceModel, Long> {
    List<VehicleServiceModel> findByCustomerNameContaining(String customerName);
}