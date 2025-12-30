package com.insurance.www.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.www.model.*;
import java.util.List;


public interface ClaimRepository extends JpaRepository<Claim, Long> {
	List<Claim> findByClaimIdContainingIgnoreCase(String claimId);

    List<Claim> findByCustomerNameContainingIgnoreCase(String customerName);

    List<Claim> findByStatus(String status);
}