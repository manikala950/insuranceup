package com.insurance.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.www.model.Customer;
import com.insurance.www.repository.CustomerRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo repo;

    /* ================= CREATE ================= */
    @Override
    public Customer saveCustomer(Customer customer) {

        if (repo.existsByEmail(customer.getEmail()))
            throw new RuntimeException("Email already exists");

        if (repo.existsByAadhaar(customer.getAadhaar()))
            throw new RuntimeException("Aadhaar already exists");

        if (repo.existsByPan(customer.getPan()))
            throw new RuntimeException("PAN already exists");

        if (repo.existsByNumber(customer.getNumber()))
            throw new RuntimeException("Mobile number already exists");

        if (repo.existsByBankAccount(customer.getBankAccount()))
            throw new RuntimeException("Bank account already exists");

        return repo.save(customer);
    }

    /* ================= GET ALL ================= */
    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    /* ================= GET BY ID ================= */
    @Override
    public Customer getCustomerByCustId(String custId) {
        return repo.findById(custId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found: " + custId));
    }

    /* ================= FILTER ================= */
    @Override
    public List<Customer> filterCustomers(
            String name,
            String state,
            String district,
            String mandal
    ) {
        return repo.filterCustomers(
            name == null || name.isBlank() ? null : name,
            state == null || state.isBlank() ? null : state,
            district == null || district.isBlank() ? null : district,
            mandal == null || mandal.isBlank() ? null : mandal
        );
    }

    /* ================= UPDATE ================= */
    @Override
    public Customer updateCustomer(String custId, Customer updated) {

        Customer existing = getCustomerByCustId(custId);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setNumber(updated.getNumber());
        existing.setAddress(updated.getAddress());
        existing.setBankName(updated.getBankName());
        existing.setBankAccount(updated.getBankAccount());
        existing.setIfsc(updated.getIfsc());

        if (updated.getAadhaarFilePath() != null)
            existing.setAadhaarFilePath(updated.getAadhaarFilePath());

        if (updated.getPanFilePath() != null)
            existing.setPanFilePath(updated.getPanFilePath());

        if (updated.getPhotoPath() != null)
            existing.setPhotoPath(updated.getPhotoPath());

        return repo.save(existing);
    }

    /* ================= DELETE ================= */
    @Override
    public void deleteCustomer(String custId) {
        repo.delete(getCustomerByCustId(custId));
    }
}
