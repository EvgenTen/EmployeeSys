package com.evgen.springproj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evgen.springproj.exception.ResourceNotFoundException;
import com.evgen.springproj.model.Employee;
import com.evgen.springproj.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		System.out.println("get list");
		return employeeRepository.findAll();
	}
	
	// create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		System.out.println(employee.getFirstName() + " created");
		return employeeRepository.save(employee);
	}
	
	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmloyeeById(@PathVariable Long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee with id " + id +" not found in database"));
		return ResponseEntity.ok(employee);
	}
	
	// Update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee with id " + id +" not found in database"));
		
		System.out.println(employee.getId() + " updated");

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());

		Employee updatedEmployee = employeeRepository.save(employee);
		System.out.println(employee.getFirstName() + " updated");

		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	// Delete Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee with id " + id +" not found in database"));
		
		System.out.println(employee.getFirstName() + " deleted");

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", true);
		return ResponseEntity.ok(response);		
	}
	
	
	
}
