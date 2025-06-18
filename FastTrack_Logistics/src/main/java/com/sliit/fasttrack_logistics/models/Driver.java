package com.sliit.fasttrack_logistics.models;

public class Driver {

    private int id;
    private String name;
    private String licenseNumber;
    private String contact;
    private String status;
    private String Email;

    // Constructors
    public Driver() {
    }

    public Driver(int id, String name, String licenseNumber, String contact, String status, String Email) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.contact = contact;
        this.status = status;
        this.Email = Email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
