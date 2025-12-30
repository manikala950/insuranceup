package com.insurance.www.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.www.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    // ✅ ALWAYS RETURNS ONE LATEST AGENT
    Optional<Agent> findTopByOrderByAgentIdDesc();

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByAadhar(String aadhar);
    boolean existsByBankAccount(String bankAccount);
    boolean existsByLicenseNumber(String licenseNumber);
}
