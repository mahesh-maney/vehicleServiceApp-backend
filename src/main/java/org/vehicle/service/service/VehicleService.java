package org.vehicle.service.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vehicle.service.model.VehicleServiceModel;
import org.vehicle.service.repository.VehicleServiceRepository;
import org.vehicle.service.specifications.VehicleServiceSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private VehicleServiceRepository repository;

    public List<VehicleServiceModel> getAllRecords() {
        logger.debug("Fetching all records");
        return repository.findAll();
    }

    public VehicleServiceModel addRecord(VehicleServiceModel record) {
        logger.debug("Adding a new record: {}", record);
        return repository.save(record);
    }

    public void deleteRecord(Long id) {
        logger.debug("Deleting record with id: {}", id);
        repository.deleteById(id);
    }

    public VehicleServiceModel updateRecord(Long id, VehicleServiceModel record) {
        logger.debug("Updating record with id: {}", id);
        VehicleServiceModel existingRecord = repository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.setCustomerName(record.getCustomerName());
        existingRecord.setContactNumber(record.getContactNumber());
        existingRecord.setPlateNumber(record.getPlateNumber());
        existingRecord.setOdometer(record.getOdometer());
        existingRecord.setChassisNo(record.getChassisNo());
        existingRecord.setMake(record.getMake());
        existingRecord.setModel(record.getModel());
        existingRecord.setYear(record.getYear());
        existingRecord.setTechnician(record.getTechnician());
        existingRecord.setJobDescription(record.getJobDescription());
        return repository.save(existingRecord);
    }

    public List<VehicleServiceModel> searchRecords(VehicleServiceModel record) {
        logger.debug("Searching records with criteria: {}", record);
        Specification<VehicleServiceModel> spec = Specification.where(VehicleServiceSpecification.hasCustomerName(record.getCustomerName()))
                .and(VehicleServiceSpecification.hasPlateNumber(record.getPlateNumber()))
                .and(VehicleServiceSpecification.hasTechnician(record.getTechnician()))
                .and(VehicleServiceSpecification.hasDate(record.getDate()))
                .and(VehicleServiceSpecification.hasChassisNo(record.getChassisNo()));

        return repository.findAll(spec);
    }

    public String uploadFile(MultipartFile file) {

        if (file.isEmpty() || !validateFileExtension(file)) {
            throw new IllegalArgumentException("Invalid file. Please upload a .xlsx file.");
        }

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            List<VehicleServiceModel> records = new ArrayList<>();

            for (Row row : sheet) {
                // Skip the header row
                if (row.getRowNum() == 0) continue;

                VehicleServiceModel record = new VehicleServiceModel();

                record.setDate(row.getCell(0).getLocalDateTimeCellValue().toLocalDate());
                record.setInvoice(row.getCell(1).getStringCellValue());
                record.setCustomerName(row.getCell(2).getStringCellValue());
                record.setContactNumber(row.getCell(3).getStringCellValue());
                record.setPlateNumber(row.getCell(4).getStringCellValue());
                record.setOdometer((int) row.getCell(5).getNumericCellValue());
                record.setChassisNo(row.getCell(6).getStringCellValue());
                record.setMake(row.getCell(7).getStringCellValue());
                record.setModel(row.getCell(8).getStringCellValue());
                record.setYear((int) row.getCell(9).getNumericCellValue());
                record.setTechnician(row.getCell(10).getStringCellValue());
                record.setJobDescription(row.getCell(11).getStringCellValue());

                records.add(record);
            }
            logger.debug("File uploaded and records saved successfully!");
            repository.saveAll(records); // Save all records to MongoDB
            return "File uploaded and " + sheet.getLastRowNum()  + " records saved successfully!";

        } catch (IOException e) {
            throw new RuntimeException("Failed to read the uploaded file", e);
        }
    }

    private boolean validateFileExtension(MultipartFile file){
        return file.getOriginalFilename().endsWith(".xlsx")
                || file.getOriginalFilename().endsWith(".xls");
    }
}