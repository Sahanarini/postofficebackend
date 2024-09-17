package com.postal.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postal.model.Employee;
import com.postal.model.PostOfficeHead;
import com.postal.serviceimplementation.EmployeeServiceImp;
import com.postal.serviceimplementation.PostOfficeServiceImp;

@RestController
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImp service;
//	@Autowired
//	private PostOfficeServiceImp ser;
//	

	@PostMapping("/addEmployee")
	public String employeeAdd(@RequestBody Employee emp) {
		service.addEmp(emp);
		return "added";

	}

	@GetMapping("/getAllEmployeebypincode/{pincode}")
	public List<Employee> getAllemployeebypincode(@PathVariable("pincode") int pincode) {
		return service.getEmployeeByPincode(pincode);

	}

	@PostMapping("/addEmp")
	public String empAdd(@RequestParam("empName") String empName, @RequestParam("empMobile") long empMobile,
			@RequestParam("area") String area, @RequestParam("slot") int slot,
			@RequestParam("deliverydate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliverydate,
			@RequestParam("deliverytime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime deliverytime,
			@RequestParam("empEmail") String empEmail, @RequestParam("password") String password,
			@RequestParam("pincode") Integer pincode) {
		String msg = "";
		try {
			service.addEmpp(empName, empMobile, area, slot, deliverydate, deliverytime, empEmail, password, pincode);
			msg = "added";
		} catch (Exception e) {
			msg = "failed";

		}
		return msg;

	}

	@GetMapping("/getAllEmployee")
	public List<Employee> getAll() {
		return service.getAllEmployee();

	}

	@PutMapping("/empUpdate")
	public String empUpdate(@RequestBody Employee emp) {
		service.updateEmp(emp);
		return "User Object Updated";

	}

	@GetMapping("/EmployeeLogin/{empId}/{password}")
	public ResponseEntity<?> validateLogin(@PathVariable("empId") int empId,
			@PathVariable("password") String password) {
		try {
			Employee emp = service.Login(empId, password);
			if (emp != null) {
				return ResponseEntity.ok(emp);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@GetMapping("/findById/{empId}")
	public ResponseEntity<?> findEmp(@PathVariable("empId") int empId) {
		try {
			Employee emp = service.findById(empId);
			if (emp != null) {
				return ResponseEntity.ok(emp);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
			}
		} catch (Exception e) {
			// Log the exception with a logging framework
			// For example: logger.error("Error finding employee by ID", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the request");
		}
	}

//	@SuppressWarnings("unchecked")
//	@PutMapping("/{empId}/slot")
//	public ResponseEntity<Employee> updateSlot(@PathVariable("empId") int empId, @RequestParam("slot") int slot) {
//		try {
//			// Call the service method to update the slot
//			Employee updatedEmployee = service.updateSlot(empId, slot);
//			if (updatedEmployee != null) {
//				return ResponseEntity.ok(updatedEmployee);
//			}
//		} catch (RuntimeException e) {
//			System.out.println("not found");
//		}
//		return (ResponseEntity<Employee>) ResponseEntity.badRequest();
//	}

}
