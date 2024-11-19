package org.vehicle.service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vehicle.service.model.VehicleServiceModel;

import java.util.List;

@Repository
public interface VehicleServiceRepository extends JpaRepository<VehicleServiceModel, Long> {
    List<VehicleServiceModel> findByCustomerNameContaining(String customerName);

    List<VehicleServiceModel> findAll(Specification<VehicleServiceModel> spec);
}
