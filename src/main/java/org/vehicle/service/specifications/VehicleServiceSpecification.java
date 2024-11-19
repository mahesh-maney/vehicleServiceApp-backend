package org.vehicle.service.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.vehicle.service.model.VehicleServiceModel;

import java.time.LocalDate;

public class VehicleServiceSpecification {

    public static Specification<VehicleServiceModel> hasCustomerName(String customerName) {
        return (root, query, criteriaBuilder) -> 
            customerName == null ? null : criteriaBuilder.like(root.get("customerName"), "%" + customerName + "%");
    }

    public static Specification<VehicleServiceModel> hasPlateNumber(String plateNumber) {
        return (root, query, criteriaBuilder) -> 
            plateNumber == null ? null : criteriaBuilder.equal(root.get("plateNumber"), plateNumber);
    }

    public static Specification<VehicleServiceModel> hasTechnician(String technician) {
        return (root, query, criteriaBuilder) -> 
            technician == null ? null : criteriaBuilder.like(root.get("technician"), "%" + technician + "%");
    }

    public static Specification<VehicleServiceModel> hasDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> 
            date == null ? null : criteriaBuilder.equal(root.get("date"), date);
    }

    public static Specification<VehicleServiceModel> hasChassisNo(String chassisNo) {
        return (root, query, criteriaBuilder) -> 
            chassisNo == null ? null : criteriaBuilder.equal(root.get("chassisNo"), chassisNo);
    }
}
