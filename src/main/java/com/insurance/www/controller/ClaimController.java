package com.insurance.www.controller;

import com.insurance.www.model.Claim;
import com.insurance.www.service.ClaimService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = {
        "http://localhost:9505",
        "https://insurancefront.netlify.app"
})
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    /* ================= UPLOAD CLAIM ================= */
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadClaim(
            @RequestParam String claimId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate claimDate,
            @RequestParam String customerName,
            @RequestParam String policy,
            @RequestParam Double claimAmount,
            @RequestParam(required = false) String notes,
            @RequestPart(required = false) MultipartFile[] files
    ) {
        try {
            Claim claim = new Claim();
            claim.setClaimId(claimId);
            claim.setClaimDate(claimDate);
            claim.setCustomerName(customerName);
            claim.setPolicy(policy);
            claim.setClaimAmount(claimAmount);
            claim.setNotes(notes);
            claim.setStatus("Pending");

            Claim saved = claimService.saveClaim(
                    claim,
                    files != null ? files : new MultipartFile[]{}
            );

            return ResponseEntity.ok(saved);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Duplicate claim ID"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(Map.of("message", "Failed to upload claim"));
        }
    }

    /* ================= GET ALL CLAIMS ================= */
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    /* ================= SEARCH CLAIMS ================= */
    @GetMapping("/search")
    public ResponseEntity<List<Claim>> searchClaims(
            @RequestParam(required = false) String claimId,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(
                claimService.searchClaims(claimId, customerName, status)
        );
    }
}
