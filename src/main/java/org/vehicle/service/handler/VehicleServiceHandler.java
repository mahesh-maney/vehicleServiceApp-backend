package org.vehicle.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.vehicle.service.model.VehicleServiceModel;
import org.vehicle.service.service.VehicleService;
import java.util.List;

@Component
public class VehicleServiceHandler {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceHandler.class);

    @Autowired
    VehicleService service;

    public List<VehicleServiceModel> getAllRecords() {
        logger.debug("Fetching all records");
        return service.getAllRecords();
    }

    public VehicleServiceModel addRecord(VehicleServiceModel record) {
        logger.debug("Adding a new record: {}", record);
        return service.addRecord(record);
    }

    public VehicleServiceModel updateRecord(Long id, VehicleServiceModel record) {
        logger.debug("Updating record with id: {}", id);
        return service.updateRecord(id, record);
    }

    public void deleteRecord(Long id) {
        logger.debug("Deleting record with id: {}", id);
        service.deleteRecord(id);
    }

    public List<VehicleServiceModel> searchRecords(VehicleServiceModel record) {
        logger.debug("Searching records with criteria: {}", record);
        return service.searchRecords(record);
    }


    public String uploadFile(MultipartFile file) {
        return service.uploadFile(file);
    }
}