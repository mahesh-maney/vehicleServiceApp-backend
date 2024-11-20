package org.vehicle.service.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "vehicleService")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleServiceModel {

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
    private String jobDescription;

    // Getters and setters
}
