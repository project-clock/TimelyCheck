package com.americinn.timelycheck.model;

public class StaffModel {
    private String name;
    private String department;
    private int empId;
    private int digitalPin;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDigitalPin() {
        return digitalPin;
    }

    public void setDigitalPin(int digitalPin) {
        this.digitalPin = digitalPin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
