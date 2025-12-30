package com.insurance.www.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.insurance.www.model.Agent;
import com.insurance.www.repository.AgentRepository;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepo;

    // ================= AGENT ID GENERATOR =================
    @Transactional
    public String generateAgentId(String stateCode, String districtCode, String mandalCode) {

        int next = 1;

        var lastOpt = agentRepo.findTopByOrderByAgentIdDesc();
        if (lastOpt.isPresent()) {
            String lastId = lastOpt.get().getAgentId();
            next = Integer.parseInt(lastId.substring(lastId.length() - 4)) + 1;
        }

        return stateCode + "-" +
               districtCode + "-" +
               mandalCode + "-" +
               String.format("%04d", next);
    }

    // ================= CREATE =================
    @Transactional
    public Agent createAgent(Agent agent) {

        if (agentRepo.existsByEmail(agent.getEmail()))
            throw new RuntimeException("Email already exists");
        if (agentRepo.existsByUsername(agent.getUsername()))
            throw new RuntimeException("Username already exists");
        if (agentRepo.existsByAadhar(agent.getAadhar()))
            throw new RuntimeException("Aadhar already exists");
        if (agentRepo.existsByBankAccount(agent.getBankAccount()))
            throw new RuntimeException("Bank account already exists");
        if (agentRepo.existsByLicenseNumber(agent.getLicenseNumber()))
            throw new RuntimeException("License number already exists");

        agent.setAgentId(
            generateAgentId(
                agent.getStateCode(),
                agent.getDistrictCode(),
                agent.getMandalCode()
            )
        );

        return agentRepo.save(agent);
    }

    // ================= READ =================
    public List<Agent> getAllAgents() {
        return agentRepo.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Agent not found"));
    }

    // ================= UPDATE =================
    @Transactional
    public Agent updateAgent(Long id, Agent updated) {
        Agent agent = getAgentById(id);

        agent.setName(updated.getName());
        agent.setNumber(updated.getNumber());
        agent.setAddress(updated.getAddress());

        return agentRepo.save(agent);
    }

    // ================= DELETE =================
    @Transactional
    public void deleteAgent(Long id) {
        if (!agentRepo.existsById(id)) {
            throw new RuntimeException("Agent not found");
        }
        agentRepo.deleteById(id);
    }
}
