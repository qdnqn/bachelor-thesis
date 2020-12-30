package com.app.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Employees 
{
    private List<Employee> employeeList;
    
    public List<Employee> getEmployeeList() {
        if(employeeList == null) {
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }

    public Employee getEmployee(Integer id) {
        for (int i = 0; i < employeeList.size(); i++){
          if(employeeList.get(i).getId() == id) {
            return employeeList.get(i);
          }
        }

        return null;
    }
 
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
