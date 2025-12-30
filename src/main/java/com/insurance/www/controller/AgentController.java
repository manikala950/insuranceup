package com.insurance.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insurance.www.model.Agent;
import com.insurance.www.service.AgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:8080")
public class AgentController {

    @Autowired
    private AgentService agentService;

    // ✅ CREATE
    @PostMapping("/add-agent")
    public ResponseEntity<?> addAgent(@RequestBody Agent agent) {
        return ResponseEntity.ok(agentService.createAgent(agent));
    }

    // ✅ READ ALL
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAgent(
            @PathVariable Long id,
            @RequestBody Agent updatedAgent
    ) {
        return ResponseEntity.ok(agentService.updateAgent(id, updatedAgent));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.ok("Agent deleted successfully");
    }
}
