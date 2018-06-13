package com.company.app;

public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    
    //Basic getters and setters
    public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
    }
    public Long getemployeeId(){
        return employeeId;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
}
