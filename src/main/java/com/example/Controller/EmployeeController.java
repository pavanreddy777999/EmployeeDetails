package com.example.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.EmptyDOJFoundException;
import com.example.Exception.EmptyEmailFoundException;
import com.example.Exception.EmptyEmployeeIDException;
import com.example.Exception.EmptyPhoneNumberFoundException;
import com.example.Exception.InvalidEmailFoundException;
import com.example.Exception.InvalidSalaryFoundException;
import com.example.Exception.NoFirstNameFoundException;
import com.example.Exception.NoLastNameFoundException;
import com.example.Model.Employee;
import com.example.Service.EmployeeService;



@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class EmployeeController {
	
	
	private ResponseEntity responseEntity;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@GetMapping("/healthCheck")
	public ResponseEntity<?> healthCheck() {

		try {
			System.out.println("---Check Health- v1");

			responseEntity = new ResponseEntity<>("Health is OK V1", HttpStatus.OK);
		} catch (Exception eobj) {
			System.out.println("exception in healthCheck" + eobj);
			responseEntity = new ResponseEntity<>("Unable to get Health ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	@PostMapping("/saveEmployeeDetails")
    public ResponseEntity<?> saveEmployeeDetails(@RequestBody Employee employee) throws EmptyEmailFoundException,NoLastNameFoundException, EmptyEmployeeIDException,EmptyDOJFoundException, NoFirstNameFoundException, InvalidEmailFoundException, EmptyPhoneNumberFoundException, InvalidSalaryFoundException
    {

		
		if(employee.getEmployeeID().isEmpty())
		{
			throw new EmptyEmployeeIDException();
		}
		if(employee.getDoj()==null)
		{
			throw new EmptyDOJFoundException();
		}
		
		if(employee.getFirstName().isEmpty())
		{
			throw new NoFirstNameFoundException();
		}
		if(employee.getLastName().isEmpty())
		{
			throw new NoLastNameFoundException();
		}
		
		if(employee.getEmail().isEmpty())
		{
			throw new EmptyEmailFoundException();
		}
		if(employee.getPhoneNumber().isEmpty())
		{
			throw new EmptyPhoneNumberFoundException();
			
		}
		if(employee.getSalary()<0)
		{
			throw new InvalidSalaryFoundException();
		}
		
		else if(!employee.getEmail().contains("@gmail.com"))
		{
			
			throw new InvalidEmailFoundException();
		}
		
        try
        {
            responseEntity = new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.OK);
        }
        catch(Exception eobj)
        {
            System.out.println("exception in saving employee details"+eobj);
            responseEntity = new ResponseEntity<>("Unable to save emp details",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
 
	
	@GetMapping("/getEmployeeTaxDeductionByEmployeeID/{employeeID}")
    public ResponseEntity<?> getEmployeeTaxDeductionByEmployeeID(@PathVariable String employeeID)
    {

        try
        {

            responseEntity = new ResponseEntity<>(employeeService.returnEmployeeTaxDeduction(employeeID),HttpStatus.OK);
        }
        catch(Exception eobj)
        {
            System.out.println("exception in getting tax deductions by employee id"+eobj);
            responseEntity = new ResponseEntity<>("Unable to get tax deductions by employee id",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
 
 

}
