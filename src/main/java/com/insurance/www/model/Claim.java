package com.insurance.www.model;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class Claim {
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		private String claimId;
		private LocalDate claimDate;
		private String customerName;
		private String policy;
		private Double claimAmount;
		private String status; 
		
		
		@Column(length = 1000)
		private String notes;
		
		
		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }
		
		
		public String getClaimId() { return claimId; }
		public void setClaimId(String claimId) { this.claimId = claimId; }
		
		
		public LocalDate getClaimDate() { return claimDate; }
		public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }
		
		
		public String getCustomerName() { return customerName; }
		public void setCustomerName(String customerName) { this.customerName = customerName; }
		
		
		public String getPolicy() { return policy; }
		public void setPolicy(String policy) { this.policy = policy; }
		
		
		public Double getClaimAmount() { return claimAmount; }
		public void setClaimAmount(Double claimAmount) { this.claimAmount = claimAmount; }
		
		
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getNotes() { return notes; }
		public void setNotes(String notes) { this.notes = notes; }
}