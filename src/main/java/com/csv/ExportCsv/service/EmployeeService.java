package com.csv.ExportCsv.service;



import com.csv.ExportCsv.entity.Employee;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    ByteArrayInputStream generateCsvFile();
}

