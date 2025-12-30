package com.insurance.www.model;

import jakarta.persistence.*;

@Entity
@Table(name = "agent")
public class Agent {

    // ================= PRIMARY KEY =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // DB primary key

    // ================= BASIC DETAILS =================
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String number;

    @Column(unique = true, nullable = false)
    private String aadhar;

    @Column(unique = true, nullable = false)
    private String bankAccount;

    private String address;

    // ================= LOCATION CODES =================
    // Example: AP, 23, 01
    private String stateCode;      // AP
    private String districtCode;   // 23
    private String mandalCode;     // 01

    // ================= BUSINESS AGENT ID =================
    // Example: AP-23-01-0001
    @Column(name = "agent_id", unique = true)
    private String agentId;

    @Column(unique = true, nullable = false)
    private String licenseNumber;

    // ================= LOGIN DETAILS =================
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(name = "reset_token")
    private String resetToken;

    // ================= CONSTRUCTORS =================

    public Agent() {
        // Required by JPA
    }

    public Agent(Long id, String name, String email, String number,
                 String aadhar, String bankAccount, String address,
                 String stateCode, String districtCode, String mandalCode,
                 String agentId, String licenseNumber,
                 String username, String password, String resetToken) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.aadhar = aadhar;
        this.bankAccount = bankAccount;
        this.address = address;
        this.stateCode = stateCode;
        this.districtCode = districtCode;
        this.mandalCode = mandalCode;
        this.agentId = agentId;
        this.licenseNumber = licenseNumber;
        this.username = username;
        this.password = password;
        this.resetToken = resetToken;
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }

    public String getAadhar() {
        return aadhar;
    }
    
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getBankAccount() {
        return bankAccount;
    }
    
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateCode() {
        return stateCode;
    }
    
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }
    
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getMandalCode() {
        return mandalCode;
    }
    
    public void setMandalCode(String mandalCode) {
        this.mandalCode = mandalCode;
    }

    public String getAgentId() {
        return agentId;
    }
    
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetToken() {
        return resetToken;
    }
    
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    // ================= toString (OPTIONAL) =================
    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", mandalCode='" + mandalCode + '\'' +
                ", agentId='" + agentId + '\'' +
                '}';
    }
}
