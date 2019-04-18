/*
 * @author sodiq oyedotun stark
 * modified 18/4/2019
 */
package com.stark.springboot.cruddemo.service;

import java.util.List;

import com.stark.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);

	public void deleteById(int theId);

}
