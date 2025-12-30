package com.insurance.www.controller;


import com.insurance.www.model.*;
import com.insurance.www.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


import java.time.LocalDate;


@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = {"http://localhost:9505",
		"https://insurancefront.netlify.app"
	    }
	)
public class ClaimController {


	private final ClaimService claimService;
	
	
	public ClaimController(ClaimService claimService) {
	this.claimService = claimService;
		}	
	
		
		@PostMapping(value = "/upload", consumes = "multipart/form-data")
		public Claim uploadClaim(
		@RequestParam String claimId,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate claimDate,
		@RequestParam String customerName,
		@RequestParam String policy,
		@RequestParam Double claimAmount,
		@RequestParam(required = false) String notes,
		@RequestPart(required = false) MultipartFile[] files
		) throws Exception {
		
				
			Claim claim = new Claim();
			claim.setClaimId(claimId);
			claim.setClaimDate(claimDate);
			claim.setCustomerName(customerName);
			claim.setPolicy(policy);
			claim.setClaimAmount(claimAmount);
			claim.setNotes(notes);
	
	
		return claimService.saveClaim(claim, files != null ? files : new MultipartFile[]{});
		}
		
		@GetMapping
		public List<Claim> getAllClaims() {
		    return claimService.getAllClaims();
		}

		@GetMapping("/search")
		public List<Claim> searchClaims(
		        @RequestParam(required = false) String claimId,
		        @RequestParam(required = false) String customer,
		        @RequestParam(required = false) String status
		) {
		    return claimService.searchClaims(claimId, customer, status);
		}
}