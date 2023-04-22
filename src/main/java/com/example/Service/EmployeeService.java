package com.example.Service;

import com.example.Model.EmployeeTaxDeduction;
import com.example.Exception.EmployeeAlreadyExistsException;
import com.example.Model.Employee;

public interface EmployeeService {

	
//	Employee saveEmployee(Employee employee);

	Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException;
	
	EmployeeTaxDeduction returnEmployeeTaxDeduction(String employeeID);
	
	
}
