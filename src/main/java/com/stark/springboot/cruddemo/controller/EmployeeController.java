/*
 * @author sodiq oyedotun stark
 */
package com.stark.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stark.springboot.cruddemo.entity.Employee;
import com.stark.springboot.cruddemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	/**
	 *  LIST all employees
	 */
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployee = employeeService.findAll();
		theModel.addAttribute("employees", theEmployee);
		return "/employees/list-employees";
	}
	
	/**
	 *  ADD an employee
	 */
	@GetMapping("/add-employee")
	public String addEmployee(Model theModel) {
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "/employees/add-employee-form";
	}
	
	/**
	 *  POST | CREATE an employee
	 */
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		// set id to 0
		theEmployee.setId(0);
		// save the employee
		employeeService.save(theEmployee);
		// return the employee
		return theEmployee;
	}
	
	/**
	 *  SAVE an employee
	 */
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/employee/list";
	}
	
	/**
	 *  DELETE an employee
	 */
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employee id - " + employeeId;
	}

}
