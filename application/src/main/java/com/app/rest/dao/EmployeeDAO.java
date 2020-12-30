package com.app.rest.dao;

import org.springframework.stereotype.Repository;

import com.app.rest.model.Employee;
import com.app.rest.model.Employees;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    
    static 
    {
        list.getEmployeeList().add(new Employee(1, "Adnan", "Selimovic", "adnan.selimovic@email.com"));
        list.getEmployeeList().add(new Employee(2, "Test", "User", "test.user@email.com"));
        list.getEmployeeList().add(new Employee(3, "Dummy", "Dummy", "dummy.dummy@email.com"));
        list.getEmployeeList().add(new Employee(4, "Adnan1", "Selimovic1", "adnan.selimovic1@email.com"));
        list.getEmployeeList().add(new Employee(5, "Test1", "User1", "test.user1@email.com"));
        list.getEmployeeList().add(new Employee(6, "Dummy1", "Dummy1", "dummy.dummy1@email.com"));
    }
    
    public Employees getAllEmployees() 
    {
        return list;
    }

    public Employee getEmployee(Integer id){
        return list.getEmployee(id);
    }
    
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
}
