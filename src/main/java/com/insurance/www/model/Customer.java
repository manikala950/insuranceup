package com.insurance.www.model;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "customer",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "aadhaar"),
        @UniqueConstraint(columnNames = "pan"),
        @UniqueConstraint(columnNames = "number"),
        @UniqueConstraint(columnNames = "bankAccount")
    }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GenericGenerator(name = "cvs_id", strategy = "com.insurance.www.CustomerIdGenerator")
    @GeneratedValue(generator = "cvs_id")
    @Column(name = "cust_id")
    private String custId;

    private String name;
    private String email;
    private long number;
    private long aadhaar;
    private String pan;
    private String address;

    /* LOCATION (NAMES) */
    private String state;
    private String district;
    private String mandal;

    /* BANK */
    private String bankName;
    private String bankAccount;
    private String ifsc;

    /* AGENT */
    private String agentId;

    /* FILE PATHS */
    private String aadhaarFilePath;
    private String panFilePath;
    private String photoPath;
}
