package com.insurance.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.www.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

    boolean existsByEmail(String email);
    boolean existsByAadhaar(long aadhaar);
    boolean existsByPan(String pan);
    boolean existsByNumber(long number);
    boolean existsByBankAccount(String bankAccount);

    @Query("""
        SELECT c FROM Customer c
        WHERE (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:state IS NULL OR c.state = :state)
          AND (:district IS NULL OR c.district = :district)
          AND (:mandal IS NULL OR c.mandal = :mandal)
    """)
    List<Customer> filterCustomers(
        String name,
        String state,
        String district,
        String mandal
    );
}
