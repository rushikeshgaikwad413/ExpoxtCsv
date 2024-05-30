package com.csv.ExportCsv.ServiceImpl;


import com.csv.ExportCsv.Repository.EmployeeRepository;
import com.csv.ExportCsv.entity.Employee;
import com.csv.ExportCsv.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public ByteArrayInputStream generateCsvFile() {
        List<Employee> employees = getAllEmployees();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader("id", "title", "description", "cover_Image"))) {
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getTitle(), employee.getDescription(), employee.getCoverImage());
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error while generating CSV file", e);
        }
    }
}

