package org.vehicle.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vehicle.service.handler.VehicleServiceHandler;
import org.vehicle.service.model.VehicleServiceModel;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-service")
@CrossOrigin(origins = "*")
public class VehicleServiceController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceController.class);

    @Autowired
    private VehicleServiceHandler handler;

    @GetMapping
    public List<VehicleServiceModel> getAllRecords() {
        logger.debug("Fetching all records");
        return handler.getAllRecords();
    }

    @PostMapping
    public VehicleServiceModel addRecord(@RequestBody VehicleServiceModel record) {
        logger.debug("Adding a new record: {}", record);
        return handler.addRecord(record);
    }

    @PutMapping("/{id}")
    public VehicleServiceModel updateRecord(@PathVariable Long id, @RequestBody VehicleServiceModel record) {
        logger.debug("Updating record with id: {}", id);
        return handler.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        logger.debug("Deleting record with id: {}", id);
        handler.deleteRecord(id);
    }

    @GetMapping("/search")
    public List<VehicleServiceModel> searchRecords(@RequestParam(required = false) VehicleServiceModel record) {
        logger.debug("Searching records with criteria: {}", record);
        return handler.searchRecords(record);
    }

    // Endpoint to upload .xlsx file
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return handler.uploadFile(file);
    }

}