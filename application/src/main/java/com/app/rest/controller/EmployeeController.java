package com.app.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.rest.exception.NotFoundException;

import com.app.rest.dao.EmployeeDAO;
import com.app.rest.model.Employee;
import com.app.rest.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController 
{
    @Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Employees getEmployees() 
    {
        return employeeDao.getAllEmployees();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    @ResponseBody
    public Employee getEmployee(@PathVariable Integer id) 
    {
        Employee temp = employeeDao.getEmployee(id);

        if(temp == null){
            throw new NotFoundException("Employee with id: " + id + " doesn't exist.");
        } 

        return temp;
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee) 
                 throws Exception 
    {       
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        
        employee.setId(id);
        employeeDao.addEmployee(employee);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
        
        return ResponseEntity.created(location).build();
    }
}
