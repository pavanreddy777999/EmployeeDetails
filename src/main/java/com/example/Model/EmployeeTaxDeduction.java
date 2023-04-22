package com.example.Model;

public class EmployeeTaxDeduction {
	
	private String employeeID;
	private String firstName;
	private String lastName;
	private int yearlySalary;
	private double taxAmount;
	private double cessAmount;
	
	public EmployeeTaxDeduction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeTaxDeduction(String employeeID, String firstName, String lastName, int yearlySalary, double taxAmount,
			double cessAmount) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearlySalary = yearlySalary;
		this.taxAmount = taxAmount;
		this.cessAmount = cessAmount;
	}
	@Override
	public String toString() {
		return "EmployeeTaxDeduction [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", yearlySalary=" + yearlySalary + ", taxAmount=" + taxAmount + ", cessAmount=" + cessAmount + "]";
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(int yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(double cessAmount2) {
		this.cessAmount = cessAmount2;
	}
	
	
	

}
