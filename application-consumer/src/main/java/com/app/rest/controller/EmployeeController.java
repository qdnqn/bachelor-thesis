package com.app.rest.controller;

import com.app.rest.exception.InternalErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.exception.NotFoundException;

import com.app.rest.model.Employee;
import com.app.rest.model.Employees;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController 
{
    @Value( "${ServiceUrl}" )
    private String ServiceUrl;
    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(path="/", produces = "application/json")
    public Employees getEmployees() 
    {
        RestTemplate restTemplate = new RestTemplate();

        String url = ServiceUrl+"employees/";
        ResponseEntity<Employees> response = restTemplate.getForEntity(url, Employees.class);
        
        if(response.getStatusCode() != HttpStatus.OK){
            throw new InternalErrorException("Remote server failed to produce data.");
        }  else {
            this.logger.info("HTTP 200 OK from employees microservice: Get employees data.");
        }

        return response.getBody();
    }

    @GetMapping(path="/{id}", produces = "application/json")
    @ResponseBody
    public Employee getEmployee(@PathVariable Integer id) 
    {
        RestTemplate restTemplate = new RestTemplate();

        String url = ServiceUrl+"employees/" + id;
        ResponseEntity<Employee> response = restTemplate.getForEntity(url, Employee.class);

        if(response.getStatusCode() != HttpStatus.OK){
            throw new NotFoundException("Employee with id: " + id + " doesn't exist.");
        } else {
            this.logger.info("HTTP 200 OK from employees microservice: Found employee with id: " + id + ".");
        }

        return response.getBody();
    }
}
