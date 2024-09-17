package com.postal.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.postal.model.Employee;

public interface EmployeeService {

	public void addEmp(Employee emp);

	public void delEmp(int empId);

	public void updateEmp(Employee emp);

	public List<Employee> getAllEmployee();

	public Employee findById(int empId);

//	public Employee updateSlot(int empId, int slot);

	public Employee Login(int empId, String password);
	
	public List<Integer> fetchAllEmp();
	
	public List<Employee> getEmployeeByPincode(int pincode);

	public void addEmpp(String empName, long empMobile, String area, int slot, LocalDate deliverydate,
			LocalTime deliverytime, String empEmail, String password, Integer pincode);

}
