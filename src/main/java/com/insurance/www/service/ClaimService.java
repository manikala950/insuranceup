package com.insurance.www.service;


import com.insurance.www.model.*;
import com.insurance.www.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;


@Service
public class ClaimService {

	
	private final ClaimRepository claimRepository;
	private final ClaimDocumentRepository documentRepository;
	
	
		public ClaimService(ClaimRepository claimRepository, ClaimDocumentRepository documentRepository) {
		this.claimRepository = claimRepository;
		this.documentRepository = documentRepository;
		}
	
		public Claim saveClaim(Claim claim, MultipartFile[] files) throws IOException {
		Claim savedClaim = claimRepository.save(claim);
		
	
			for (MultipartFile file : files) {
			ClaimDocument doc = new ClaimDocument();
			doc.setFileName(file.getOriginalFilename());
			doc.setFileType(file.getContentType());
			doc.setData(file.getBytes());
			doc.setClaim(savedClaim);
			documentRepository.save(doc);
		}
		return savedClaim;
	}
		// ✅ GET ALL CLAIMS
	    public List<Claim> getAllClaims() {
	        return claimRepository.findAll();
	    }

	    // ✅ SEARCH CLAIMS (used by React filters)
	    public List<Claim> searchClaims(String claimId, String customer, String status) {

	        if (claimId != null && !claimId.isBlank()) {
	            return claimRepository
	                    .findByClaimIdContainingIgnoreCase(claimId);
	        }

	        if (customer != null && !customer.isBlank()) {
	            return claimRepository
	                    .findByCustomerNameContainingIgnoreCase(customer);
	        }

	        if (status != null && !status.isBlank()) {
	            return claimRepository.findByStatus(status);
	        }

	        return claimRepository.findAll();
	    }
}