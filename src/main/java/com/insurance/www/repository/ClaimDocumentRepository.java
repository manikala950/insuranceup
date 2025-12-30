package com.insurance.www.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.www.model.ClaimDocument;


public interface ClaimDocumentRepository extends JpaRepository<ClaimDocument, Long> {}