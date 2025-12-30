package com.insurance.www.service;

import java.util.List;
import com.insurance.www.model.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerByCustId(String custId);

    List<Customer> filterCustomers(
        String name,
        String state,
        String district,
        String mandal
    );

    Customer updateCustomer(String custId, Customer updatedCustomer);

    void deleteCustomer(String custId);
}
