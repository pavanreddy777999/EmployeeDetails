package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Model.EmployeeTaxDeduction;
import com.example.Exception.EmployeeAlreadyExistsException;
import com.example.Model.Employee;
import com.example.Repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException {
		// TODO Auto-generated method stub
		Employee saveEmployee=new Employee();
		
		Optional<Employee> employee1=employeeRepository.findById(employee.getEmployeeID());
		if(employee1.isEmpty())
		{
			saveEmployee=employeeRepository.save(employee);
			
		}
		else 
		{
			 throw new EmployeeAlreadyExistsException();
		}
		
		
		return saveEmployee;
	}


	@Override
	public EmployeeTaxDeduction returnEmployeeTaxDeduction(String employeeID) {
		// TODO Auto-generated method stub
		
		EmployeeTaxDeduction employeeTaxDeduction=new EmployeeTaxDeduction();
		
		Optional<Employee> employee=employeeRepository.findById(employeeID);
		
		
		double tax=0;
		double cessAmount=0;
		
		LocalDate doj =employee.get().getDoj();  
		
		int year=doj.getYear();
		int month=doj.getMonthValue();
		List<Integer> monthswith30days=new ArrayList<>();
		monthswith30days.add(11);
		monthswith30days.add(4);
		monthswith30days.add(6);
		monthswith30days.add(9);
		List<Integer> monthswith31days=new ArrayList<>();
		monthswith30days.add(1);
		monthswith30days.add(3);
		monthswith30days.add(5);
		monthswith30days.add(7);
		monthswith30days.add(8);
		monthswith30days.add(10);
		monthswith30days.add(12);
		
		
		
		
		int months=0;
		int days=0;
		
		if(doj.getMonthValue()<4)
		{
			LocalDate localDate=LocalDate.of(year, 3, 31);
			Period period = Period.between(doj, localDate);
			months=period.getMonths();
			days=period.getDays();
		}
		else
		{
			LocalDate localDate=LocalDate.of(year+1, 3, 31);
			Period period = Period.between(doj, localDate);
			months=period.getMonths();
			days=period.getDays();
			
		}
		
        int monthlySalary = employee.get().getSalary();
		
		
		double dailySalary=0;
		if(monthswith30days.contains(month))
		{
			dailySalary=monthlySalary/(30-days);
		}
		else if(monthswith31days.contains(month))
				{
			dailySalary=monthlySalary/(31-days);
				}
		else if(year%4==0)
		{
			dailySalary=monthlySalary/(29-days);
		}
		else
		{
			dailySalary=monthlySalary/(28-days);
		}
		
		int annualSalary=(int) ((monthlySalary*months) + dailySalary) ;
	
		
		
		System.out.println("months and days"+months+"---"+days);
		
		if(employee.isPresent())
		{
		
			
			if(annualSalary<=250000)
			{
				tax=0;
			}
			else if(annualSalary>250000 && annualSalary<=500000)
			{
				int amountToBeTaxed=annualSalary-250000;
				tax= amountToBeTaxed*0.05;
				
			}
			else if(annualSalary>500000 && annualSalary<=1000000)
			{
				tax= ((annualSalary-500000)*0.1)+ ((250000)*0.05);
			
			}
			else if(annualSalary>1000000)
			{
				tax= ((annualSalary-1000000)*0.2)+ ((500000)*0.1)+(250000*0.05);
			}
			if(annualSalary>2500000)
			{
				int cessAmountToBeTaxed=annualSalary-2500000;
				cessAmount= cessAmountToBeTaxed*0.02;
				System.out.println("cess amount"+cessAmount);
			}
			
			
			
		}
		employeeTaxDeduction.setEmployeeID(employeeID);
		employeeTaxDeduction.setCessAmount(cessAmount);
		employeeTaxDeduction.setFirstName(employee.get().getFirstName());
		employeeTaxDeduction.setLastName(employee.get().getLastName());
		employeeTaxDeduction.setTaxAmount(tax);
		employeeTaxDeduction.setYearlySalary(annualSalary);
		
		return employeeTaxDeduction;
	}
	

}
