package org.vehicle.service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehicle_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String invoice;
    private String customerName;
    private String contactNumber;
    private String plateNumber;
    private Integer odometer;
    private String chassisNo;
    private String make;
    private String model;
    private Integer year;
    private String technician;
    @Lob
    private String jobDescription;

    // Getters and setters
}
