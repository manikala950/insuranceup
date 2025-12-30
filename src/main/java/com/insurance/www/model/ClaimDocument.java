package com.insurance.www.model;


import jakarta.persistence.*;


@Entity
public class ClaimDocument {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String fileName;
private String fileType;


@Lob
@Column(columnDefinition = "LONGBLOB")
private byte[] data;


@ManyToOne
@JoinColumn(name = "claim_id")
private Claim claim;


public Long getId() { return id; }
public void setId(Long id) { this.id = id; }


public String getFileName() { return fileName; }
public void setFileName(String fileName) { this.fileName = fileName; }


public String getFileType() { return fileType; }
public void setFileType(String fileType) { this.fileType = fileType; }


public byte[] getData() { return data; }
public void setData(byte[] data) { this.data = data; }


public Claim getClaim() { return claim; }
public void setClaim(Claim claim) { this.claim = claim; }
}